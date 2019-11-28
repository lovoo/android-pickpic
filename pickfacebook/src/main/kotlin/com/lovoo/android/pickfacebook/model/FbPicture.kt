package com.lovoo.android.pickfacebook.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

/**
 * Model class that represents Facebook Picture.
 *
 * @param id the picture Identifier
 * @param url the online path to the picture
 * @param width the picture width
 * @param height the picture height
 */
data class FbPicture(
        val id: String,
        val url: String,
        val width: Int,
        val height: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<FbPicture> {
        override fun createFromParcel(parcel: Parcel) = FbPicture(parcel)
        override fun newArray(size: Int): Array<FbPicture?> = arrayOfNulls(size)
    }

    /**
     * @return the [Uri] for this picture
     */
    fun getUri(): Uri = Uri.parse(url)
}
