package com.lovoo.android.pickcore.loader

import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.database.MergeCursor
import android.database.sqlite.SQLiteDiskIOException
import android.provider.MediaStore
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
class GalleryLoader(context: Context)

    : CursorLoader(
        context,
        query,
        projection,
        selection,
        selectArguments,
        "${MediaStore.Images.Media.DATE_TAKEN} DESC") {

    private val columns = arrayOf(
            MediaStore.Files.FileColumns._ID,
            COLUMN_NAME_ID,
            COLUMN_NAME_DISPLAY_NAME,
            MediaStore.MediaColumns.DATA,
            COLUMN_NAME_COUNT)

    override fun loadInBackground(): Cursor {
        try {
            val galleries = super.loadInBackground()
            val allEntry = MatrixCursor(columns)

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

        } catch (brokenFileException: SQLiteDiskIOException) {
            return MatrixCursor(columns)
        }
    }

    companion object {
        private const val COLUMN_NAME_ID = "bucket_id"
        private const val COLUMN_NAME_DISPLAY_NAME = "bucket_display_name"
        private const val COLUMN_NAME_COUNT = "count"

        private val query = MediaStore.Files.getContentUri("external")
        private val projection = arrayOf(
                MediaStore.Files.FileColumns._ID,
                COLUMN_NAME_ID,
                COLUMN_NAME_DISPLAY_NAME,
                MediaStore.MediaColumns.DATA,
                "COUNT(*) AS $COLUMN_NAME_COUNT")
        private const val group = ") GROUP BY ($COLUMN_NAME_ID"
        private const val selection = "${MediaStore.Files.FileColumns.MEDIA_TYPE}=? AND ${MediaStore.MediaColumns.SIZE}>0$group"
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
        fun convert(cursor: Cursor) = GalleryLib(
                cursor.getString(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_ID)),
                cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA)),
                cursor.getString(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_DISPLAY_NAME)),
                cursor.getLong(cursor.getColumnIndex(GalleryLoader.COLUMN_NAME_COUNT)))
    }
}