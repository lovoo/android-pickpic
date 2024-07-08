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

import com.facebook.FacebookException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.lovoo.android.pickcore.model.Gallery
import org.json.JSONObject

/**
 * Collection of methods to convert Facebook Response data to own Data Models.
 */
object Converter {

    /**
     * Transforms the source localized message and cause to a normal [Throwable].
     *
     * @param exception the [FacebookException] or null
     * @return a [Throwable] from [FacebookException] or null
     */
    fun convert(exception: FacebookException?): Throwable? {
        if (exception == null) return null

        return Throwable(exception.localizedMessage, exception.cause)
    }

    /**
     * @param json the response from Facebook gallery request
     * @return mutable list of [Gallery]s
     */
    fun convertToGalleries(json: JSONObject?) = mutableListOf<Gallery>().apply {
        json?.optJSONArray("data")?.let { array ->
            val albumArray = Gson().fromJson(array.toString(), Array<FbAlbum>::class.java)
            addAll(
                albumArray.filter { !it.id.isNullOrBlank() && !it.name.isNullOrBlank() }
                    .map {
                        Gallery(
                            it.id ?: "",
                            it.picture?.data?.url ?: "",
                            it.name ?: "",
                            it.count?.toLong() ?: 0L,
                        )
                    },
            )
        }
    }

    /**
     * @param json the response from Facebook picture request
     * @return mutable list of [FbPicture]s
     */
    fun convertToPictures(json: JSONObject?) = mutableListOf<FbPicture>().apply {
        json?.optJSONArray("data")?.let { array ->
            val photosArray = Gson().fromJson(array.toString(), Array<FbPhoto>::class.java)
            addAll(
                photosArray.filter { !it.id.isNullOrBlank() }
                    .map {
                        val photo = it.images?.first() ?: FbImage()
                        FbPicture(
                            it.id ?: "",
                            photo.url ?: "",
                            photo.width ?: 0,
                            photo.height ?: 0,
                        )
                    },
            )
        }
    }

    /**
     * Model class that represents Facebook Album.
     *
     * @param id the identifier
     * @param name the name of the album
     * @param count the complete picture count
     * @param picture the album data
     */
    data class FbAlbum(
        @SerializedName("id") val id: String? = "",
        @SerializedName("name") val name: String? = "",
        @SerializedName("count") val count: Int? = 0,
        @SerializedName("picture") val picture: FbAlbumData? = null,
    )

    /**
     * @param data the album cover
     */
    data class FbAlbumData(
        @SerializedName("data") val data: FbCover? = null,
    )

    /**
     * @param isPlaceholder true if [FbCover] is a non user picture
     * @param url the path to the cover image
     */
    data class FbCover(
        @SerializedName("is_silhouette") val isPlaceholder: Boolean? = false,
        @SerializedName("url") val url: String? = "",
    )

    /**
     * @param id the picture identifier
     * @param images a list of available [FbImage]s
     */
    data class FbPhoto(
        @SerializedName("id") val id: String? = "",
        @SerializedName("images") val images: List<FbImage>? = emptyList(),
    )

    /**
     * @param url the path to the image
     * @param height the image height or 0
     * @param width the image width or 0
     */
    data class FbImage(
        @SerializedName("source") val url: String? = "",
        @SerializedName("height") val height: Int? = 0,
        @SerializedName("width") val width: Int? = 0,
    )
}
