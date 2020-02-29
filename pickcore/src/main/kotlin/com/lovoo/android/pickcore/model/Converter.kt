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

/**
 * Convert a PickPic Picture to Library Picture
 */
fun Picture.convertToLib() = PictureLib(
        id, mimeType, size
)

/**
 * Convert a Library Picture to PickPic Picture
 */
fun PictureLib.convertToUi() = Picture(
        id, mimeType ?: "", size ?: 0L
)

/**
 * Convert a PickPic Gallery to Library Gallery
 */
fun Gallery.convertToLib() = GalleryLib(
        id, coverPath, name, count
)

/**
 * Convert a Library Gallery to PickPic Gallery
 */
fun GalleryLib.convertToUi() = Gallery(
        id ?: "", coverPath ?: "", name ?: "", count ?: 0L
)
