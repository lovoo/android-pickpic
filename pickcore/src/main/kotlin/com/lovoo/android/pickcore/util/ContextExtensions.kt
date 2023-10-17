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
package com.lovoo.android.pickcore.util

import android.annotation.SuppressLint
import android.content.*
import android.os.Build

fun isMinimumQ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

fun isMinimumR() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

/**
 * Registers given [receiver] for all Android versions, including Android 13+, where the "EXPORTED" nature of the receiver must be explicitly passed
 * to the parameters.
 *
 * @param isExported indicates if the BroadcastReceiver is exported or not (i.e. available to external apps). False by default.
 */
@SuppressLint("UnspecifiedRegisterReceiverFlag")
fun Context.registerReceiverSafely(
    receiver: BroadcastReceiver,
    filter: IntentFilter,
    isExported: Boolean = false
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val exportedFlag = if (isExported) Context.RECEIVER_EXPORTED else Context.RECEIVER_NOT_EXPORTED
        registerReceiver(receiver, filter, exportedFlag)
    } else {
        registerReceiver(receiver, filter)
    }
}