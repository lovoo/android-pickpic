package com.lovoo.android.pickcore.model

import android.content.ContentUris
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

/**
 * Model class that represents an PickPic Picture.
 *
 * @param id the picture Identifier
 * @param mimeType the file type of the picture
 * @param size the file size
 */
data class Picture(
        val id: Long,
        val mimeType: String,
        val size: Long
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString() ?: "",
            parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(mimeType)
        parcel.writeLong(size)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Picture> {
        override fun createFromParcel(parcel: Parcel) = Picture(parcel)
        override fun newArray(size: Int): Array<Picture?> = arrayOfNulls(size)
    }

    fun getUri(): Uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
}