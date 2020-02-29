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
package com.lovoo.android.pickcore.adapter

import android.database.Cursor
import android.database.DataSetObserver
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

/**
 * An [RecyclerView.Adapter] implementation that handle [Cursor] as data source.
 *
 * @param cursor the loaded [Cursor] or null by default
 *
 * @see RecyclerViewCursorAdapter.changeCursor
 */
abstract class RecyclerViewCursorAdapter<VH : RecyclerView.ViewHolder>(private var cursor: Cursor? = null) : RecyclerView.Adapter<VH>() {

    private var rowIDColumn: Int = 0
    private var dataValid: Boolean by Delegates.observable(false) { _, _, _ ->
        notifyDataSetChanged()
    }

    private val dataObserver = object : DataSetObserver() {
        override fun onChanged() {
            dataValid = true
        }

        override fun onInvalidated() {
            dataValid = false
        }
    }

    private var isRegistered = false

    init {
        init()
    }

    private fun init() {
        setHasStableIds(true)
        swapCursor(cursor)
    }

    abstract fun onBindViewHolder(holder: VH, cursor: Cursor)

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (!dataValid) {
            throw IllegalStateException("this should only be called when the cursor is valid")
        }

        cursor?.let {
            if (!it.moveToPosition(position)) {
                throw IllegalStateException("couldn't move cursor to position $position")
            }
            onBindViewHolder(holder, it)
        }
    }

    /**
     * @param position the requested position
     * @return the cursor item identifier or [RecyclerView.NO_ID]
     */
    override fun getItemId(position: Int): Long {
        val c = cursor ?: return RecyclerView.NO_ID

        return when (dataValid && c.moveToPosition(position)) {
            true -> c.getLong(rowIDColumn)
            else -> RecyclerView.NO_ID
        }
    }

    /**
     * @param position the requested position
     * @param transform the transformation to convert the [Cursor] entry to a model class
     *
     * @return the transformed model for the [Cursor] item at this position or null
     */
    fun <T> getItem(position: Int, transform: (Cursor) -> T): T? {
        val c = cursor ?: return null
        if (position < 0 || itemCount <= position) return null

        return when (dataValid && c.moveToPosition(position)) {
            true -> transform.invoke(c)
            else -> null
        }
    }

    /**
     * @param transform the transformation to convert the [Cursor] entry to a model class
     *
     * @return the transformed models as list for all [Cursor] items
     */
    fun <T> getItems(transform: (Cursor) -> T): List<T> {
        val list = mutableListOf<T>()

        val c = cursor ?: return list
        for (i in 0 until itemCount) {
            when (dataValid && c.moveToPosition(i)) {
                true -> list.add(transform.invoke(c))
                else -> {
                } // no-op
            }
        }

        return list
    }

    /**
     * @return the amount of items that the [Cursor] has or 0
     */
    override fun getItemCount() = when (dataValid) {
        true -> cursor?.count ?: 0
        else -> 0
    }

    /**
     * Replace the current [Cursor] with a new one and close the old one if needed.
     *
     * @param cursor the new loaded [Cursor]
     */
    fun changeCursor(cursor: Cursor?) {
        swapCursor(cursor)?.close()
    }

    private fun swapCursor(newCursor: Cursor?): Cursor? {
        if (newCursor === cursor) {
            return null
        }

        val oldCursor = cursor
        if (isRegistered) {
            isRegistered = false
            oldCursor?.unregisterDataSetObserver(dataObserver)
        }

        cursor = newCursor?.apply {
            registerDataSetObserver(dataObserver)
            isRegistered = true
            rowIDColumn = getColumnIndexOrThrow("_id")
            dataValid = true
        } ?: run {
            rowIDColumn = -1
            dataValid = false
            return null
        }
        return oldCursor
    }
}
