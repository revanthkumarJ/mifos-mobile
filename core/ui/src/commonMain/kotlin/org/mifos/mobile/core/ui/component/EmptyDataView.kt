/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.ui.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.mifos.mobile.core.designsystem.icons.MifosIcons
import org.mifos.mobile.core.designsystem.theme.MifosMobileTheme
import org.mifos.mobile.core.ui.utils.DevicePreviews
import mifos_mobile.core.ui.generated.resources.Res
import mifos_mobile.core.ui.generated.resources.no_internet

@Composable
fun EmptyDataView(
    error: StringResource,
    modifier: Modifier = Modifier.fillMaxSize(),
    icon: DrawableResource?=null,
    errorString: String? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 12.dp),
            painter = if (icon != null) {
                painterResource(icon)
            } else {
                MifosIcons.Error
            },
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary,
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = errorString ?: stringResource(error),
            style = TextStyle(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center,
        )
    }
}

@DevicePreviews
@Composable
private fun EmptyDataViewPreview(
    modifier: Modifier = Modifier,
) {
    MifosMobileTheme {
        EmptyDataView(
            error = Res.string.no_internet,
            modifier = modifier,
        )
    }
}
