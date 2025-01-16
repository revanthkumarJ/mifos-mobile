/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.ui.utils

// import android.app.Activity
// import android.content.Context
// import android.util.TypedValue
// import androidx.annotation.AttrRes
// import androidx.annotation.ColorInt
// import androidx.core.graphics.ColorUtils
// import androidx.core.view.WindowCompat
//
// fun Activity.setStatusBarColor(@ColorInt color: Int) {
//    if (!window.decorView.isInEditMode) {
//        window.statusBarColor = color
//        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
//            ColorUtils.calculateLuminance(color) > 0.5
//    }
// }
//
// @ColorInt
// fun Context.getThemeAttributeColor(@AttrRes colorAttribute: Int): Int {
//    val typedValue = TypedValue()
//    theme.resolveAttribute(colorAttribute, typedValue, true)
//    return typedValue.data
// }

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    val isLightColor = androidx.compose.ui.graphics.ColorUtils.calculateLuminance(color) > 0.5
    systemUiController.setStatusBarColor(
        color = color,
        darkIcons = isLightColor,
    )
}
