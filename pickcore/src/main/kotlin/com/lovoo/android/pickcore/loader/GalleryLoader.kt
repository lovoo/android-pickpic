/**
 * Copyright 2018 LOVOO GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lovoo.android.pickcore.loader

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.database.MergeCursor
import android.database.sqlite.SQLiteDiskIOException
import android.net.Uri
import android.provider.MediaStore
import androidx.loader.content.CursorLoader
import com.lovoo.android.pickcore.Constants
import com.lovoo.android.pickcore.model.GalleryLib
import com.lovoo.android.pickcore.util.aboveQ

/**
 * A [CursorLoader] implementation that fetch album information from external [MediaStore.Files]
 * database. The current [Cursor] should be converted to [GalleryLib] model.
 *
 * @param context the app [Context]
 *
 * @see GalleryLoader.convert
 */
class GalleryLoader(
    context: Context
) : CursorLoader(
    context,
    query,
    projection,
    selection,
    selectArguments,
    "${MediaStore.Images.Media.DATE_TAKEN} DESC"
) {

    private val columns = if (aboveQ()) arrayOf(
        MediaStore.Files.FileColumns._ID,
        COLUMN_NAME_ID,
        COLUMN_NAME_DISPLAY_NAME,
        MediaStore.Images.Media._ID,
        COLUMN_NAME_COUNT
    ) else arrayOf(
        MediaStore.Files.FileColumns._ID,
        COLUMN_NAME_ID,
        COLUMN_NAME_DISPLAY_NAME,
        MediaStore.MediaColumns.DATA,
        COLUMN_NAME_COUNT
    )

    override fun loadInBackground(): Cursor {
        return try {
            val galleries = super.loadInBackground()
            val allEntry = MatrixCursor(columns)
            if (aboveQ()) {
                loadCursorPostQ(galleries, allEntry)
            } else {
                loadCursorPreQ(galleries, allEntry)
            }
        } catch (brokenFileException: SQLiteDiskIOException) {
            MatrixCursor(columns)
        }
    }

    private fun loadCursorPreQ(galleries: Cursor?, allEntry: MatrixCursor): Cursor {
        var totalCount = 0
        var allAlbumCoverPath = ""

        while (galleries?.moveToNext() == true) {
            totalCount += galleries.getInt(galleries.getColumnIndex(COLUMN_NAME_COUNT))
        }
        if (galleries?.moveToFirst() == true) {
            allAlbumCoverPath = galleries.getString(galleries.getColumnIndex(MediaStore.MediaColumns.DATA))
        }

        allEntry.addRow(arrayOf(-1, -1, Constants.All_FOLDER_NAME, allAlbumCoverPath, totalCount.toString()))

        return MergeCursor(arrayOf(allEntry, galleries))
    }

    private fun loadCursorPostQ(galleries: Cursor?, allEntry: MatrixCursor): Cursor {
        var totalCount = 0L
        var allAlbumCoverUri: Uri? = null

        // Pseudo GROUP BY
        val countMap: MutableMap<Long, Long> = HashMap()
        galleries?.let {
            while (it.moveToNext()) {
                val bucketId = it.getLong(it.getColumnIndex(COLUMN_NAME_ID))
                var count = countMap[bucketId]
                if (count == null) {
                    count = 1L
                } else {
                    count++
                }
                countMap[bucketId] = count
            }
        }

        val otherAlbums = MatrixCursor(columns)
        galleries?.let {
            if (it.moveToFirst()) {
                allAlbumCoverUri = getUri(it)
                val done: MutableSet<Long> = HashSet()
                do {
                    val bucketId = it.getLong(it.getColumnIndex(COLUMN_NAME_ID))
                    if (done.contains(bucketId)) continue

                    val fileId = it.getLong(it.getColumnIndex(MediaStore.Files.FileColumns._ID)).toString()
                    val bucketDisplayName = it.getString(it.getColumnIndex(COLUMN_NAME_DISPLAY_NAME))
                    val uri = getUri(it)
                    val count = countMap[bucketId] ?: 0L

                    otherAlbums.addRow(
                        arrayOf(
                            fileId,
                            bucketId.toString(),
                            bucketDisplayName,
                            uri.toString(),
                            count.toString()
                        )
                    )

                    done.add(bucketId)
                    totalCount += count
                } while (it.moveToNext())
            }
        }

        allEntry.addRow(
            arrayOf(
                -1,
                -1,
                Constants.All_FOLDER_NAME,
                allAlbumCoverUri?.toString(),
                totalCount
            )
        )

        return MergeCursor(arrayOf<Cursor>(allEntry, otherAlbums))
    }

    companion object {
        private const val COLUMN_NAME_ID = "bucket_id"
        private const val COLUMN_NAME_DISPLAY_NAME = "bucket_display_name"
        private const val COLUMN_NAME_COUNT = "count"

        private val query = MediaStore.Files.getContentUri("external")
        private val projection = if (aboveQ()) arrayOf(
            MediaStore.Files.FileColumns._ID,
            COLUMN_NAME_ID,
            COLUMN_NAME_DISPLAY_NAME,
            MediaStore.Images.Media._ID
        ) else
            arrayOf(
                MediaStore.Files.FileColumns._ID,
                COLUMN_NAME_ID,
                COLUMN_NAME_DISPLAY_NAME,
                MediaStore.MediaColumns.DATA,
                "COUNT(*) AS $COLUMN_NAME_COUNT"
            )
        private val group = if (aboveQ()) "" else ") GROUP BY ($COLUMN_NAME_ID"
        private val selection = "${MediaStore.Files.FileColumns.MEDIA_TYPE}=? AND ${MediaStore.MediaColumns.SIZE}>0$group"
        private val selectArguments = arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString())

        /**
         * Creates a new instance of [GalleryLoader].
         *
         * @param context the app [Context]
         * @return new instance
         */
        fun newInstance(context: Context): CursorLoader = GalleryLoader(context)

        /**
         * Converts the current [Cursor] entry to [GalleryLib] model.
         *
         * @param cursor the [Cursor]
         * @return the [GalleryLib] object with the data from the [Cursor]
         */
        fun convert(cursor: Cursor) = if (aboveQ()) GalleryLib(
            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ID)),
            getUri(cursor)?.toString(),
            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISPLAY_NAME)),
            cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_COUNT))
        ) else GalleryLib(
            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ID)),
            cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA)),
            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISPLAY_NAME)),
            cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_COUNT))
        )

        private fun getUri(cursor: Cursor): Uri? {
            val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID))
            val contentUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            return ContentUris.withAppendedId(contentUri, id)
        }
    }
}
