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
package com.lovoo.android.pickcore.destination

import android.os.Environment
import android.os.Parcel
import android.os.Parcelable
import com.lovoo.android.pickcore.contract.CameraDestination
import com.lovoo.android.pickcore.contract.createTempFile
import java.io.File

/**
 * Implementation of [CameraDestination] for pictures that should be stored in public directory.
 */
class PublicDirectory : CameraDestination {
    override val authority = ""
    override val directory: File
    override val file: File?

    /**
     * @param type the directory type, default is Environment.DIRECTORY_PICTURES
     * @param fileName the requested name, default is a random JPG filename
     */
    constructor(type: String = Environment.DIRECTORY_PICTURES, fileName: String = "") {
        this.directory = Environment.getExternalStoragePublicDirectory(type)
        this.file = createTempFile(fileName)
    }

    private constructor(parcel: Parcel) {
        directory = parcel.readSerializable() as File
        file = parcel.readSerializable() as File?
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(directory)
        parcel.writeSerializable(file)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<PublicDirectory> {
        override fun createFromParcel(parcel: Parcel) = PublicDirectory(parcel)
        override fun newArray(size: Int): Array<PublicDirectory?> = arrayOfNulls(size)
    }
}
