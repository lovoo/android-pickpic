package com.lovoo.android.pickcore.model

/**
 * Model class that represents an Android PictureLib loaded from the Database.
 *
 * @param id the picture Identifier within the database
 * @param mimeType the file type of the picture or null
 * @param size the file size or null
 */
data class PictureLib(
        val id: Long,
        val mimeType: String? = "",
        val size: Long? = 0L
)