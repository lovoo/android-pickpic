package com.lovoo.android.pickcore.destination

import android.os.Parcel
import android.os.Parcelable
import com.lovoo.android.pickcore.contract.CameraDestination
import com.lovoo.android.pickcore.contract.createTempFile
import java.io.File


/**
 * Implementation of [CameraDestination] for pictures that should be stored in private app directory.
 * Keep in mind that other apps does not have access to this directory / file
 * unless you provide a own content provider.
 */
class PrivateDirectory : CameraDestination {

    override val authority: String
    override val directory: File
    override val file: File?

    /**
     * @param authority the manifest registered authority
     * @param directory the requested directory within the private app storage
     * @param fileName the requested name, default is a random JPG filename
     */
    constructor(authority: String, directory: File, fileName: String = "") {
        this.directory = directory
        this.authority = authority
        this.file = createTempFile(fileName)
    }

    private constructor(parcel: Parcel) {
        authority = parcel.readString() ?: ""
        directory = parcel.readSerializable() as File
        file = parcel.readSerializable() as File?
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(authority)
        parcel.writeSerializable(directory)
        parcel.writeSerializable(file)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<PrivateDirectory> {
        override fun createFromParcel(parcel: Parcel) = PrivateDirectory(parcel)
        override fun newArray(size: Int): Array<PrivateDirectory?> = arrayOfNulls(size)
    }
}

/**
 * Copies the file to the default [PublicDirectory] and deletes it from the private directory.
 */
fun PrivateDirectory.moveToPublicDirectory(): PublicDirectory {
    return file?.moveToPublicDirectory() ?: PublicDirectory("")
}

/**
 * Copies the file to the default [PublicDirectory] and deletes it.
 */
fun File.moveToPublicDirectory(): PublicDirectory {
    val target = PublicDirectory(fileName = name ?: "")
    try {
        copy(this, target.file)
        this.delete()

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return target
}

@Throws(Exception::class)
private fun copy(src: File?, dst: File?) {
    if (src == null || dst == null) return
    dst.mkdirs()
    src.copyTo(dst, true)
}
