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
package com.lovoo.android.pickapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes

/**
 * The configuration for [com.lovoo.android.pickapp.view.PickPicActivity] behaviour.
 *
 * @see [com.lovoo.android.pickapp.factory.PickDependencies]
 * @see [com.lovoo.android.pickcore.PickPicProvider]
 *
 * @param style the style resource that should be used (control primary color and so on) or 0
 * @param minCount the minimal count the user has to select before call to action is enabled, default 1
 * @param maxCount the maximal count the user can select at all, default 1
 * @param title the Activity title
 * @param sendText the text for the call to action or null (see PickPicConfig.isPreviewEnabled)
 * @param sendIcon the drawable resource identifier or null (see PickPicConfig.isPreviewEnabled)
 * @param header the description below the Activity title or null if not wanted
 * @param faqUrl the url that is used for the FAQ toolbar menu or null if not wanted
 */
data class PickPicConfig(
  @StyleRes val style: Int = 0,
  val minCount: Int = 1,
  val maxCount: Int = 1,
  val title: String = "",
  val sendText: String? = null,
  @DrawableRes val sendIcon: Int? = null,
  val header: String? = null,
  val faqUrl: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(style)
        parcel.writeInt(minCount)
        parcel.writeInt(maxCount)
        parcel.writeString(title)
        parcel.writeString(sendText)
        parcel.writeValue(sendIcon)
        parcel.writeString(header)
        parcel.writeString(faqUrl)
    }

    override fun describeContents() = 0

    /**
     * Used to define if preview and selectionbar has to be initialized.
     * If this is true and the user hit the selection maxCount the picker will be finished.
     *
     * @return true if sendText and sendIcon are null
     */
    fun isPreviewEnabled() = sendIcon != null || sendText != null

    companion object CREATOR : Parcelable.Creator<PickPicConfig> {
        override fun createFromParcel(parcel: Parcel) = PickPicConfig(parcel)
        override fun newArray(size: Int): Array<PickPicConfig?> = arrayOfNulls(size)
    }
}
