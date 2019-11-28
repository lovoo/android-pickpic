package com.lovoo.android.pickcore.model


/**
 * Model class that represents an Android GalleryLib Folder loaded from the Database.
 *
 * @param id the gallery Identifier within the database
 * @param coverPath the path to the cover image
 * @param name the gallery name
 * @param count the amount of pictures within this gallery
 */
data class GalleryLib(
        val id: String? = "",
        val coverPath: String? = "",
        val name: String? = "",
        val count: Long? = 0L
)