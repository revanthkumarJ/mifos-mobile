/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.datastore

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.mifos.mobile.core.datastore.model.AppSettings
import org.mifos.mobile.core.datastore.model.AppTheme
import org.mifos.mobile.core.datastore.model.MifosAppLanguage
import org.mifos.mobile.core.datastore.model.User
import org.mifos.mobile.core.datastore.model.UserData

class PreferencesHelper(private val settings: Settings) {

    fun clear() {
        val keysToPreserve = setOf(BASE_URL, TENANT)
        settings.keys.filter { it !in keysToPreserve }
            .forEach { key ->
                settings.remove(key)
            }
    }

    private fun getInt(preferenceKey: String?, preferenceDefaultValue: Int?): Int? {
        return if (preferenceKey != null && preferenceDefaultValue != null) {
            settings[preferenceKey, preferenceDefaultValue]
        } else {
            null
        }
    }

    private fun putInt(preferenceKey: String?, preferenceValue: Int?) {
        if (preferenceKey != null && preferenceValue != null) {
            settings[preferenceKey] = preferenceValue
        }
    }

    private fun getLong(preferenceKey: String?, preferenceDefaultValue: Long?): Long? {
        return if (preferenceKey == null || preferenceDefaultValue == null) {
            null
        } else {
            settings[preferenceKey, preferenceDefaultValue]
        }
    }

    private fun putLong(preferenceKey: String?, preferenceValue: Long?) {
        if (preferenceKey != null && preferenceValue != null) {
            settings[preferenceKey] = preferenceValue
        }
    }

    private fun getString(preferenceKey: String?, preferenceDefaultValue: String?): String? {
        return if (preferenceKey == null || preferenceDefaultValue == null) {
            null
        } else {
            settings[preferenceKey, preferenceDefaultValue]
        }
    }

    private fun putString(preferenceKey: String?, preferenceValue: String?) {
        if (preferenceKey != null && preferenceValue != null) {
            settings[preferenceKey] = preferenceValue
        }
    }

    private fun getBoolean(preferenceKey: String?, preferenceDefaultValue: Boolean?): Boolean? {
        return if (preferenceKey == null || preferenceDefaultValue == null) {
            null
        } else {
            settings[preferenceKey, preferenceDefaultValue]
        }
    }

    private fun putBoolean(preferenceKey: String?, preferenceValue: Boolean?) {
        if (preferenceKey != null && preferenceValue != null) {
            settings[preferenceKey] = preferenceValue
        }
    }

    fun saveToken(token: String) {
        putString(TOKEN, token)
    }

    fun clearToken() {
        putString(TOKEN, "")
    }

    private val token: String?
        get() = getString(TOKEN, "")

    var isAuthenticated: Boolean = false
        get() = !token.isNullOrEmpty()

    var userId: Long?
        get() = getLong(USER_ID, -1)
        set(value) {
            putLong(USER_ID, value)
        }

    var userName: String?
        get() = getString(USER_NAME, "")
        set(value) {
            putString(USER_NAME, value)
        }

    var tenant: String?
        get() = getString(TENANT, DEFAULT_TENANT)
        set(value) {
            putString(TENANT, value)
        }

    var passcode: String?
        get() = getString(PASSCODE, "")
        set(value) {
            putString(PASSCODE, value)
        }

    var clientId: Long?
        get() = getLong(CLIENT_ID, -1)
        set(value) {
            putLong(CLIENT_ID, value)
        }

    var clientName: String?
        get() = getString(CLIENT_NAME, "")
        set(value) {
            putString(CLIENT_NAME, value)
        }

    var officeName: String?
        get() = getString(OFFICE_NAME, "")
        set(value) {
            putString(OFFICE_NAME, value)
        }

    fun setOverviewState(state: Boolean?) {
        putBoolean(OVERVIEW_STATE, state)
    }

    fun overviewState(): Boolean? = getBoolean(OVERVIEW_STATE, true)

    fun saveGcmToken(token: String?) {
        putString(GCM_TOKEN, token)
    }

    var userProfileImage: String?
        get() = getString(PROFILE_IMAGE, null.toString())
        set(value) {
            putString(PROFILE_IMAGE, value)
        }

    val gcmToken: String?
        get() = getString(GCM_TOKEN, "")

    fun setSentTokenToServer(state: Boolean?) {
        putBoolean(SENT_TOKEN_TO_SERVER, state)
    }

    fun sentTokenToServerState(): Boolean? = getBoolean(SENT_TOKEN_TO_SERVER, false)

    fun updateConfiguration(baseUrl: String?, tenant: String?) {
        settings.apply {
            putString(BASE_URL, baseUrl)
            putString(TENANT, tenant)
        }
    }

    val baseUrl: String?
        get() = getString(BASE_URL, DEFAULT_BASE_URL)

    var appTheme: Int?
        get() = getInt(APPLICATION_THEME, AppTheme.SYSTEM.ordinal)
        set(value) {
            putInt(APPLICATION_THEME, value)
        }

    var language: String?
        get() = getString(LANGUAGE_TYPE, MifosAppLanguage.ENGLISH.code)
            ?: MifosAppLanguage.SYSTEM_LANGUAGE.code
        set(value) {
            putString(LANGUAGE_TYPE, value)
        }

    var isDefaultSystemLanguage: Boolean?
        get() = getBoolean(DEFAULT_SYSTEM_LANGUAGE, false)
        set(value) {
            putBoolean(DEFAULT_SYSTEM_LANGUAGE, value)
        }

    fun getUserData(): UserData {
        return UserData(
            clientId = clientId,
            userName = userName,
            isAuthenticated = isAuthenticated

        )
    }

    fun saveUserData(userData: UserData) {
        clientId = userData.clientId
        userName = userData.userName
        isAuthenticated=userData.isAuthenticated
    }

    fun getUser(): User {
        return User(
            userId = userId,
            userName = userName,
        )
    }

    fun saveUser(user: User) {
        userId = user.userId
        userName = user.userName

    }

    fun getAppSettings(): AppSettings {
        return AppSettings(
            tenant = tenant,
            baseUrl = baseUrl,
            passcode = passcode,
            appTheme = appTheme,
            language = language
        )
    }

    fun saveAppSettings(appSettings: AppSettings) {
        putString(TENANT, appSettings.tenant)
        putString(BASE_URL, appSettings.baseUrl)
        putString(PASSCODE, appSettings.passcode)
        putInt(APPLICATION_THEME, appSettings.appTheme)
        putString(LANGUAGE_TYPE, appSettings.language)
    }


    companion object {
        private const val USER_ID = "preferences_user_id"
        private const val TOKEN = "preferences_token"
        private const val CLIENT_ID = "preferences_client"
        private const val OFFICE_NAME = "preferences_office_name"
        private const val USER_NAME = "preferences_user_name"
        const val PASSCODE = "preferences_passcode"
        private const val OVERVIEW_STATE = "preferences_overview_state"
        private const val SENT_TOKEN_TO_SERVER = "sentTokenToServer"
        private const val GCM_TOKEN = "gcm_token"
        const val TENANT = "preferences_base_tenant"
        const val BASE_URL = "preferences_base_url_key"
        private const val PROFILE_IMAGE = "preferences_profile_image"
        const val CLIENT_NAME = "client_name"
        const val APPLICATION_THEME = "application_theme"
        const val LANGUAGE_TYPE = "language_type"
        const val DEFAULT_SYSTEM_LANGUAGE = "default_system_language"

        private const val DEFAULT_TENANT = "default"
        private const val DEFAULT_BASE_URL = "https://demo.mifos.community"
    }

    fun getStringFlowForKey(keyForString: String, settings: ObservableSettings): Flow<String?> = callbackFlow<String?> {
        trySend(settings.getStringOrNull(keyForString))

        val listener = settings.addStringOrNullListener(keyForString) { newValue ->
            trySend(newValue)
        }

        awaitClose {
            listener.deactivate()
        }
    }

    fun getIntFlowForKey(keyForInt: String, settings: ObservableSettings): Flow<Int?> = callbackFlow<Int?> {
        trySend(settings.getIntOrNull(keyForInt))

        val listener = settings.addIntOrNullListener(keyForInt) { newValue ->
            trySend(newValue)
        }

        awaitClose {
            listener.deactivate()
        }
    }
}
