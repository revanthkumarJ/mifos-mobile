/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.ui.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Canvas
import org.jetbrains.skia.Image
import org.jetbrains.skia.Matrix33
import org.jetbrains.skia.Paint
import org.jetbrains.skia.Rect
import java.io.ByteArrayOutputStream

actual object ImageUtil {
    actual val DEFAULT_MAX_WIDTH: Float = 816f
    actual val DEFAULT_MAX_HEIGHT: Float = 612f

    actual suspend fun compressImage(
        decodedBytes: ByteArray,
        maxWidth: Float,
        maxHeight: Float,
    ): ByteArray = withContext(Dispatchers.Default) {
        val bitmap = Bitmap.makeFromImage(Image.makeFromEncoded(decodedBytes))

        val (actualWidth, actualHeight) = calculateActualDimensions(bitmap.width, bitmap.height, maxWidth, maxHeight)
        val scaledBitmap = createScaledBitmap(bitmap, actualWidth, actualHeight)

        val byteArrayOutputStream = ByteArrayOutputStream()
        scaledBitmap.encodeToData()?.bytes?.let { byteArrayOutputStream.write(it) }
        byteArrayOutputStream.toByteArray()
    }

    private fun calculateActualDimensions(
        width: Int,
        height: Int,
        maxWidth: Float,
        maxHeight: Float,
    ): Pair<Int, Int> {
        val imgRatio = width.toFloat() / height
        val maxRatio = maxWidth / maxHeight

        return if (height > maxHeight || width > maxWidth) {
            when {
                imgRatio < maxRatio -> {
                    val newHeight = maxHeight.toInt()
                    val newWidth = (maxHeight * imgRatio).toInt()
                    Pair(newWidth, newHeight)
                }

                imgRatio > maxRatio -> {
                    val newWidth = maxWidth.toInt()
                    val newHeight = (maxWidth / imgRatio).toInt()
                    Pair(newWidth, newHeight)
                }

                else -> {
                    Pair(maxWidth.toInt(), maxHeight.toInt())
                }
            }
        } else {
            Pair(width, height)
        }
    }

    private fun createScaledBitmap(
        bitmap: Bitmap,
        targetWidth: Int,
        targetHeight: Int,
    ): Bitmap {
        val scaledBitmap = Bitmap().apply {
            allocPixels(targetWidth, targetHeight)
        }

        val canvas = Canvas(scaledBitmap)
        val scaleX = targetWidth.toFloat() / bitmap.width
        val scaleY = targetHeight.toFloat() / bitmap.height
        val scaleMatrix = Matrix33.makeScale(scaleX, scaleY)

        canvas.save()
        canvas.concat(scaleMatrix)
        canvas.drawBitmapRect(bitmap, Rect.makeWH(bitmap.width.toFloat(), bitmap.height.toFloat()), Paint())
        canvas.restore()

        return scaledBitmap
    }
}
