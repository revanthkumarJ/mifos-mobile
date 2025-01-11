/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.datastore.model

data class AppSettings(
    val tenant: String,
    val baseUrl: String,
    val passcode: String? = null,
    val appTheme: AppTheme = AppTheme.SYSTEM,
) {
    companion object {
        fun default() = AppSettings(
            tenant = "default_tenant",
            baseUrl = "https://default.url",
            appTheme = AppTheme.SYSTEM,
        )
    }
}
