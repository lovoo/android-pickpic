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
