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

import android.net.Uri
import com.lovoo.android.pickapp.model.Picker.PickConfig
import com.lovoo.android.pickapp.model.Picker.State
import com.lovoo.android.pickcore.model.Gallery
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * The data source for PickPicActivity.
 * It holds a map of picked Uris with the depending [Gallery].
 * [State] changes are emitted via observable and should be disposed.
 *
 * @param config the [PickConfig] to determine the limits
 */
class Picker(
    val config: PickConfig
) {

    /**
     * The data map of picked items.
     */
    val map = mutableMapOf<Uri, Gallery>()
    private val pickState: BehaviorSubject<State> = BehaviorSubject.create<State>()

    /**
     * The [State] observable that emit different state changes.
     */
    fun getObservable(): Observable<State> = pickState

    /**
     * A non null list of picked Uris from the map.
     */
    fun getPickedUris(): List<Uri> = map.mapNotNull { it.key }

    /**
     * Add or remove a picture to the map.
     *
     * @param uri the picture [Uri]
     * @param gallery the [Gallery] of the picture
     *
     * @return true if item could be added or removed, false if [PickConfig] limit is reached
     */
    fun toggle(uri: Uri, gallery: Gallery): Boolean {
        if (map.remove(uri) == null) {
            if (verifyPicking()) {
                map[uri] = gallery
                pickState.onNext(State.Add(uri, gallery))
                return true
            }
        } else {
            pickState.onNext(State.Remove(uri, gallery))
            return true
        }

        return false
    }

    /**
     * Add or remove multiple items. This calls toggle(picture: Picture, gallery: Gallery) for each entry.
     * Please make sure that both Arrays are equally sized.
     *
     * @param uris array of Uris
     * @param galleries array of [Gallery]s for each [Picture]
     */
    fun toggle(uris: Array<Uri>, galleries: Array<Gallery>) {
        if (uris.size != galleries.size) {
            throw IllegalArgumentException("Both Arrays should have equal size: ${uris.size} != ${galleries.size}.")
        }
        for (i in 0 until uris.size) {
            toggle(uris[i], galleries[i])
        }
    }

    /**
     * Explicitly remove a picture to the map.
     *
     * @param uri the [Uri]
     *
     * @return true if item could be removed
     */
    fun remove(uri: Uri): Boolean {
        val gallery = map.remove(uri)

        if (gallery != null) {
            pickState.onNext(State.Remove(uri, gallery))
            return true
        }

        return false
    }

    /**
     * Select an item by position, if not found the selection will be cleaned.
     *
     * @param position within the picked item list
     */
    fun select(position: Int) {
        select(position, getPickedUris().getOrNull(position))
    }

    /**
     * Select an item, if null the selection will be cleaned.
     *
     * @param uri of the picked item or null
     */
    fun select(uri: Uri?) {
        select(getPickedUris().indexOf(uri), uri)
    }

    private fun select(position: Int, uri: Uri?) {
        pickState.onNext(State.Select(position, uri))
    }

    private fun verifyPicking() = map.size < config.maxCount

    /**
     * @param minCount the requested amount of picked items
     * @param maxCount the allowed amount of picked items
     */
    data class PickConfig(
        val minCount: Int = 1,
        val maxCount: Int = 1
    )

    sealed class State {
        /**
         * State that notify over selection change within the picked items.
         *
         * @param position the item position within the selection or -1
         * @param uri the selected [Uri] or null if selection is cleared
         */
        data class Select(
            val position: Int,
            val uri: Uri?
        ) : State()

        /**
         * State that notifies that a new item was picked.
         *
         * @param uri the [Uri]
         * @param gallery the [Gallery] of the picture
         */
        data class Add(
            val uri: Uri,
            val gallery: Gallery
        ) : State()

        /**
         * State that notifies that a picked item was removed.
         *
         * @param uri the [Uri]
         * @param gallery the [Gallery] of the picture
         */
        data class Remove(
            val uri: Uri,
            val gallery: Gallery
        ) : State()
    }
}
