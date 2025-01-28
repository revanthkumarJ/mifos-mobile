package org.mifos.library.passcode.di

import com.russhwolf.settings.Settings
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.mifos.mobile.core.common.MifosDispatchers
import proto.org.mifos.library.passcode.data.PasscodeManager
import proto.org.mifos.library.passcode.data.PasscodePreferencesDataSource

val PasscodePreferenceModule = module {
    factory<Settings> { Settings() }
    factory { PasscodePreferencesDataSource(get(), get(named(MifosDispatchers.IO.name))) }
    factory { PasscodeManager(get(), get(named(MifosDispatchers.Unconfined.name))) }
}

