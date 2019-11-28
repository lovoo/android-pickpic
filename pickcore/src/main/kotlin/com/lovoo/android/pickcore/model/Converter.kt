package com.lovoo.android.pickcore.model

/**
 * Convert a PickPic Picture to Library Picture
 */
fun Picture.convertToLib() = PictureLib(
        id, mimeType, size
)

/**
 * Convert a Library Picture to PickPic Picture
 */
fun PictureLib.convertToUi() = Picture(
        id, mimeType ?: "", size ?: 0L
)

/**
 * Convert a PickPic Gallery to Library Gallery
 */
fun Gallery.convertToLib() = GalleryLib(
        id, coverPath, name, count
)

/**
 * Convert a Library Gallery to PickPic Gallery
 */
fun GalleryLib.convertToUi() = Gallery(
        id ?: "", coverPath ?: "", name ?: "", count ?: 0L
)