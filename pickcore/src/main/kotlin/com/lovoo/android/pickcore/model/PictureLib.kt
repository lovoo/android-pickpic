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
 * Model class that represents an Android PictureLib loaded from the Database.
 *
 * @param id the picture Identifier within the database
 * @param mimeType the file type of the picture or null
 * @param size the file size or null
 */
data class PictureLib(
  val id: Long,
  val mimeType: String? = "",
  val size: Long? = 0L
)
