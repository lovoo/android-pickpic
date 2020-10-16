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
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.loader.content.CursorLoader
import com.lovoo.android.pickcore.Constants
import com.lovoo.android.pickcore.model.GalleryLib

/**
 * A [CursorLoader] implementation that fetch album information from external [MediaStore.Files]
 * database. The current [Cursor] should be converted to [GalleryLib] model.
 *
 * @param context the app [Context]
 *
 * @see GalleryLoader.convert
 */
class GalleryLoader(context: Context) :

    CursorLoader(
        context,
        query,
        projection,
        selection,
        selectArguments,
        sortOrder()
    ) {

    private val columns = if (isNewerThanQ()) arrayOf(
        MediaStore.Files.FileColumns._ID,
        COLUMN_NAME_ID,
        COLUMN_NAME_DISPLAY_NAME,
        MediaStore.Images.Media._ID
    ) else arrayOf(
        MediaStore.Files.FileColumns._ID,
        COLUMN_NAME_ID,
        COLUMN_NAME_DISPLAY_NAME,
        MediaStore.Images.Media._ID,
        COLUMN_NAME_COUNT
    )

    override fun loadInBackground(): Cursor {
        try {
            val galleries = super.loadInBackground()
            val allEntry = MatrixCursor(columns)
            Log.e("#########", columns.joinToString())
            var totalCount = 0
            var allAlbumCoverPath = ""

            while (galleries?.moveToNext() == true) {
                totalCount += galleries.getInt(galleries.getColumnIndex(COLUMN_NAME_COUNT))
            }
            if (galleries?.moveToFirst() == true) {
                val uri: Uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, galleries.getLong(galleries.getColumnIndex(MediaStore.Images.Media._ID)))

                // allAlbumCoverPath = galleries.getString(galleries.getColumnIndex(MediaStore.MediaColumns.DATA))
            }

            allEntry.addRow(arrayOf(-1, -1, Constants.All_FOLDER_NAME, allAlbumCoverPath, totalCount.toString()))

            return MergeCursor(arrayOf(allEntry, galleries))
        } catch (brokenFileException: SQLiteDiskIOException) {
            return MatrixCursor(columns)
        }
    }

    companion object {
        private const val COLUMN_NAME_ID = "bucket_id"
        private const val COLUMN_NAME_DISPLAY_NAME = "bucket_display_name"
        private const val COLUMN_NAME_COUNT = "count"

        private val query = MediaStore.Files.getContentUri("external")
        private val projection = if (isNewerThanQ()) arrayOf(
            MediaStore.Files.FileColumns._ID,
            COLUMN_NAME_ID,
            COLUMN_NAME_DISPLAY_NAME,
            MediaStore.Images.Media._ID
        ) else
            arrayOf(
                MediaStore.Files.FileColumns._ID,
                COLUMN_NAME_ID,
                COLUMN_NAME_DISPLAY_NAME,
                MediaStore.Images.Media._ID,
                "COUNT(*) AS $COLUMN_NAME_COUNT"
            )
        private val group = if (isNewerThanQ()) "" else ") GROUP BY ($COLUMN_NAME_ID"
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
        fun convert(cursor: Cursor) = if (isNewerThanQ()) GalleryLib(
            cursor.getString(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_ID)),
            cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID)),
            cursor.getString(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_DISPLAY_NAME))
        ) else GalleryLib(
            cursor.getString(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_ID)),
            cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID)),
            cursor.getString(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_DISPLAY_NAME)),
            cursor.getLong(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_COUNT))
        )

        private fun sortOrder() = if (isNewerThanQ()) null else "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        fun isNewerThanQ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }
}
