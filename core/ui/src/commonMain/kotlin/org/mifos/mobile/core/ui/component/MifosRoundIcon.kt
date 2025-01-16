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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import mifos_mobile.core.ui.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.mifos.mobile.core.designsystem.theme.MifosMobileTheme
import org.mifos.mobile.core.ui.utils.DevicePreviews

@Composable
fun MifosRoundIcon(
    iconId: DrawableResource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .clip(CircleShape),
    ) {
        Image(
            modifier = Modifier.padding(all = 6.dp),
            painter = painterResource(iconId),
            contentDescription = contentDescription,
        )
    }
}

@DevicePreviews
@Composable
private fun MifosRoundIconPreview(
    modifier: Modifier = Modifier,
) {
    MifosMobileTheme {
        MifosRoundIcon(
            iconId = Res.drawable.core_common_circular_background,
            modifier = modifier,
        )
    }
}
