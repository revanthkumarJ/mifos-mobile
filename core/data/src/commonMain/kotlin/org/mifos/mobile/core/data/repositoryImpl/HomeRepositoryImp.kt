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

import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.mifos.mobile.core.data.repository.HomeRepository
import org.mifos.mobile.core.data.repository.NotificationRepository
import org.mifos.mobile.core.model.entity.client.Client
import org.mifos.mobile.core.model.entity.client.ClientAccounts
import org.mifos.mobile.core.network.DataManager
import org.mifospay.core.common.DataState
import org.mifospay.core.common.asDataStateFlow

class HomeRepositoryImp(
    private val dataManager: DataManager,
    private val notificationRepository: NotificationRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : HomeRepository {

    override fun clientAccounts(clientId: Long): Flow<DataState<ClientAccounts>> {
        return dataManager.clientsApi.getClientAccounts(clientId)
            .asDataStateFlow().flowOn(ioDispatcher)
    }

    override fun currentClient(clientId: Long): Flow<DataState<Client>> {
        return dataManager.clientsApi.getClientForId(clientId)
            .asDataStateFlow().flowOn(ioDispatcher)
    }

    override fun clientImage(clientId: Long): Flow<DataState<HttpResponse>> {
        return dataManager.clientsApi.getClientImage(clientId)
            .asDataStateFlow().flowOn(ioDispatcher)
    }

    override fun unreadNotificationsCount(): Flow<DataState<Int>> {
        return notificationRepository.getUnReadNotificationCount().flowOn(ioDispatcher)
    }
}
