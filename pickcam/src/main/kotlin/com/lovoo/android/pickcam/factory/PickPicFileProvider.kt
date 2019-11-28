package com.lovoo.android.pickcam.factory

import androidx.core.content.FileProvider

/**
 * Custom class to avoid double registration from Manifest merge.
 * When client app also register a FileProvider.
 */
class PickPicFileProvider : FileProvider()