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

// Should be deleted once common module is migrated
sealed class DataState<out T> {
    abstract val data: T?

    data object Loading : DataState<Nothing>() {
        override val data: Nothing? get() = null
    }

    data class Success<T>(
        override val data: T,
    ) : DataState<T>()

    data class Error<T>(
        val exception: Throwable,
        override val data: T? = null,
    ) : DataState<T>() {
        val message = exception.message.toString()
    }
}
