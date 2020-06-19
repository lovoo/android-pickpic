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
        parcel.readInt()
    )

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
