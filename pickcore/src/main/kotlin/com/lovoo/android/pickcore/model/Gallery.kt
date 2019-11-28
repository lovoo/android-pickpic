package com.lovoo.android.pickcore.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Model class that represents an PickPic Gallery Folder.
 *
 * @param id the gallery Identifier
 * @param coverPath the path to the cover image
 * @param name the gallery name
 * @param count the amount of pictures within this gallery
 */
data class Gallery(
        val id: String,
        val coverPath: String,
        val name: String,
        val count: Long
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(coverPath)
        parcel.writeString(name)
        parcel.writeLong(count)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Gallery> {
        override fun createFromParcel(parcel: Parcel) = Gallery(parcel)

        override fun newArray(size: Int) = arrayOfNulls<Gallery>(size)
    }
}