package org.mifos.mobile.feature.settings.di


import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.mifos.mobile.feature.settings.SettingsViewModel

val SettingsModule = module {
    viewModelOf(::SettingsViewModel)
}
