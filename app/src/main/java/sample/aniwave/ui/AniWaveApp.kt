/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.aniwave.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import sample.aniwave.navigation.AniWaveNavHost
import sample.aniwave.navigation.AppNavigation
import sample.aniwave.navigation.MainDestination
import sample.aniwave.ui.components.AniWaveNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AniWaveApp(
    modifier: Modifier = Modifier,
    appState: AniWaveAppState = rememberAniWaveAppState(),
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            bottomBar = {
                AniWaveNavBar(
                    navigationRoutes = MainDestination.values().asList(),
                    onNavigationSelected = appState::navigateToMainDestination,
                    currentDestination = appState.currentDestination,
                )
            },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        val destination = appState.currentMainDestination
                        if (destination != null) {
                            Text(text = stringResource(destination.displayTextId))
                        }
                    },
                )
            },
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                AniWaveNavHost(
                    appState = appState,
                    startDestination = AppNavigation.Home.route,
                )
            }
        }
    }
}
