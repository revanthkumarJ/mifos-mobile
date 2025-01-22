/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package org.mifos.mobile.core.data.repositoryImpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.mifos.mobile.core.data.repository.ThirdPartyTransferRepository
import org.mifos.mobile.core.model.entity.templates.account.AccountOptionsTemplate
import org.mifos.mobile.core.network.DataManager
import org.mifospay.core.common.DataState
import org.mifospay.core.common.asDataStateFlow

class ThirdPartyTransferRepositoryImp(
    private val dataManager: DataManager,
    private val ioDispatcher: CoroutineDispatcher,
) : ThirdPartyTransferRepository {
    override fun thirdPartyTransferTemplate(): Flow<DataState<AccountOptionsTemplate>> {
        return dataManager.thirdPartyTransferApi.accountTransferTemplate()
            .asDataStateFlow().flowOn(ioDispatcher)
    }
}
