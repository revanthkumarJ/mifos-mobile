package org.mifos.mobile.core.datastore.model

data class UserData(
    val isAuthenticated: Boolean,
    val userName: String?,
    val clientId: Long?,
)