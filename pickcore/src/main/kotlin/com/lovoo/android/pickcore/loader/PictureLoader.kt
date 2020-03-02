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

import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.database.MergeCursor
import android.database.sqlite.SQLiteDiskIOException
import android.provider.MediaStore
import androidx.loader.content.CursorLoader
import com.lovoo.android.pickcore.Constants
import com.lovoo.android.pickcore.PickPicProvider
import com.lovoo.android.pickcore.contract.CameraEngine
import com.lovoo.android.pickcore.model.GalleryLib
import com.lovoo.android.pickcore.model.PictureLib

/**
 * A [CursorLoader] implementation that fetch picture information from external [MediaStore.Files]
 * database for the given [GalleryLib]. The current [Cursor] should be converted to [PictureLib] model.
 *
 * @param context the app [Context]
 * @param gallery the current selected [GalleryLib]
 *
 * @see PictureLoader.convert
 */
class PictureLoader(context: Context, private val gallery: GalleryLib) :

    CursorLoader(
        context,
        query,
        projection,
        getSelection(gallery),
        getSelectionArgs(gallery),
        "${MediaStore.Images.Media.DATE_TAKEN} DESC") {

    private val cameraEngine: CameraEngine
        get() = PickPicProvider.cameraEngine

    override fun loadInBackground(): Cursor? {
        try {
            val cursor = super.loadInBackground()

            if (!cameraEngine.isEnabled(gallery)) {
                return cursor
            }

            val cameraCursor = MatrixCursor(projection)
            cameraCursor.addRow(arrayOf(CAMERA_ID, cameraEngine.getDisplayName(), "", 0))

            return if (cursor != null) {
                MergeCursor(arrayOf(cameraCursor, cursor))
            } else {
                cameraCursor
            }
        } catch (brokenFileException: SQLiteDiskIOException) {
            return MatrixCursor(projection)
        }
    }

    companion object {
        private const val COLUMN_NAME_ID = "bucket_id"
        const val CAMERA_ID = -1L

        private val query = MediaStore.Files.getContentUri("external")
        private val projection = arrayOf(
                MediaStore.Files.FileColumns._ID,
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.MediaColumns.SIZE)

        private fun getSelection(gallery: GalleryLib) = when (gallery.name) {
            Constants.All_FOLDER_NAME, "" -> "${MediaStore.Files.FileColumns.MEDIA_TYPE}=? AND ${MediaStore.MediaColumns.SIZE}>0"
            else -> "${MediaStore.Files.FileColumns.MEDIA_TYPE}=? AND $COLUMN_NAME_ID=? AND ${MediaStore.MediaColumns.SIZE}>0"
        }

        private fun getSelectionArgs(gallery: GalleryLib) = when (gallery.name) {
            Constants.All_FOLDER_NAME, "" -> arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString())
            else -> arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(), gallery.id)
        }

        /**
         * Creates a new instance of [PictureLoader].
         *
         * @param context the app [Context]
         * @param gallery the selected [GalleryLib]
         * @return new instance
         */
        fun newInstance(context: Context, gallery: GalleryLib): CursorLoader = PictureLoader(context, gallery)

        /**
         * Converts the current [Cursor] entry to [PictureLib] model.
         *
         * @param cursor the [Cursor]
         * @return the [PictureLib] object with the data from the [Cursor]
         */
        fun convert(cursor: Cursor) = PictureLib(
                cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)),
                cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE)),
                cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.SIZE))
        )
    }
}
