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
