/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.datastore.di

import com.russhwolf.settings.Settings
import org.koin.dsl.module
import org.mifos.mobile.core.datastore.PreferencesHelper

val PreferencesModule = module {
    single<Settings> { Settings() }
    single { PreferencesHelper(settings = get()) }
}
