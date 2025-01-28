package org.mifos.library.passcode.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.mifos.library.passcode.viewmodels.PasscodeViewModel

val PasscodeModule = module {
    includes(PasscodePreferenceModule)

    viewModelOf(::PasscodeViewModel)
}
