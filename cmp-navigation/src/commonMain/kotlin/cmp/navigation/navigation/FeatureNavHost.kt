/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-mobile/blob/master/LICENSE.md
 */
package cmp.navigation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cmp.navigation.ui.AppState
import org.mifos.mobile.feature.help.navigation.helpNavGraph
import org.mifos.mobile.feature.help.navigation.navigateToHelpScreen

const val WELCOME_ROUTE = "home_route"

@Composable
internal fun FeatureNavHost(
    appState: AppState,
//    onClickLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        route = NavGraphRoute.MAIN_GRAPH,
        startDestination = WELCOME_ROUTE,
        navController = appState.navController,
        modifier = modifier,
    ) {
        homeScreen(navController = appState.navController)
        helpNavGraph(
            findLocations = {},
            navigateBack = {},
            callHelpline = {},
            mailHelpline = {},
        )
    }
}

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(route = WELCOME_ROUTE) {
        WelcomeScreen(navController = navController)
    }
}

@Composable
fun WelcomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Welcome to Mifos Mobile")
        Button(onClick = { navController.navigateToHelpScreen() }) {
            Text("Help Screen")
        }
    }
}
