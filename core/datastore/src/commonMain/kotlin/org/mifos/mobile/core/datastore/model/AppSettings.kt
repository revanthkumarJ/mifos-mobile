package org.mifos.mobile.core.datastore.model

data class AppSettings(
    val tenant: String?,
    val baseUrl: String?,
    val passcode: String?,
    val appTheme: Int?,
    val language: String?,
)
