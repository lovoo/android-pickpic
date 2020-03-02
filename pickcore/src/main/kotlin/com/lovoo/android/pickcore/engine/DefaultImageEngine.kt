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
package com.lovoo.android.pickcore.engine

import android.content.ContentResolver
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.lovoo.android.pickcore.contract.ImageEngine
import java.io.IOException
import java.io.InputStream

/**
 * Default implementation of [ImageEngine] that loads images without any strategy or cache.
 * Be aware that this should never be used in production, because of OutOfMemory-Exceptions.
 */
class DefaultImageEngine : ImageEngine {
    override fun loadThumbnail(context: Context, size: Int, uri: Uri, target: ImageView, errorRes: Int) {
        loadImage(context, size, size, uri, target, errorRes)
    }

    override fun loadImage(context: Context, width: Int, height: Int, uri: Uri, target: ImageView, errorRes: Int) {
        target.setImageDrawable(getDrawableFromUri(context, uri, errorRes))
    }

    private fun getDrawableFromUri(context: Context, uri: Uri, errorRes: Int): Drawable? {
        val scheme = uri.scheme
        if (ContentResolver.SCHEME_CONTENT == scheme || ContentResolver.SCHEME_FILE == scheme) {
            var stream: InputStream? = null
            try {
                stream = context.contentResolver.openInputStream(uri)
                return Drawable.createFromResourceStream(context.resources, null, stream, null)
            } catch (e: Exception) {
                // no-op
            } finally {
                try {
                    stream?.close()
                } catch (e: IOException) {
                    // no-op
                }
            }
        } else {
            return Drawable.createFromPath(uri.toString())
        }
        return ContextCompat.getDrawable(context, errorRes)
    }
}
