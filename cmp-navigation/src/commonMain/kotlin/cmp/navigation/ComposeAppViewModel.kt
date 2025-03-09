/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package cmp.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mifos.library.passcode.data.PasscodeManager
import kotlinx.coroutines.flow.combine
import org.mifos.mobile.core.common.DataState
import org.mifos.mobile.core.data.repository.UserDataRepository
import org.mifos.mobile.core.datastore.model.AppTheme
import org.mifos.mobile.core.model.UserData
import org.mifos.mobile.core.datastore.UserPreferencesDataSource

class ComposeAppViewModel(
    private val userDataRepository: UserDataRepository,
    private val passcodeManager: PasscodeManager,
    private val preferenceHelper: UserPreferencesDataSource,
) : ViewModel() {

    val uiState: StateFlow<MainUiState> = combine(
        userDataRepository.userData.map { dataState ->
            when (dataState) {
                is DataState.Success -> dataState.data
                is DataState.Error -> null
                is DataState.Loading -> null
            }
        },
        preferenceHelper.settingsInfo.map { it.appTheme }
    ) { userData, themeState ->
        if (userData != null) {
            MainUiState.Success(userData, themeState)
        } else {
            MainUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainUiState.Loading
    )



    fun logOut() {
        viewModelScope.launch {
            userDataRepository.logOut()
            passcodeManager.clearPasscode()
        }
    }
}

sealed interface MainUiState {
    data object Loading : MainUiState
    data class Error(val error: String) : MainUiState
    data class Success(val userData: UserData,val themeState:AppTheme) : MainUiState
}
