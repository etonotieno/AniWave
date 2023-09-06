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
package sample.aniwave

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import org.junit.Rule
import org.junit.Test
import kotlin.properties.ReadOnlyProperty

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
        ReadOnlyProperty<Any?, String> { _, _ -> activity.getString(resId) }

    private val home by composeTestRule.stringResource(R.string.home)
    private val search by composeTestRule.stringResource(R.string.search)

    @Test
    fun firstScreen_isHomeFeed() {
        composeTestRule.apply {
            onNodeWithText(home).assertIsSelected()
        }
    }

    @Test
    fun navBar_bottomBarIsDisplayed() {
        composeTestRule.onNodeWithTag("AniWaveBottomBar").assertIsDisplayed()
    }

    @Test(expected = NoActivityResumedException::class)
    fun homeDestination_back_quitsApp() {
        composeTestRule.apply {
            // GIVEN the user navigates to the Search destination
            onNodeWithText(search).performClick()
            // and then navigates to the Home destination
            onNodeWithText(home).performClick()
            // WHEN the user uses the system button/gesture to go back
            Espresso.pressBack()
            // THEN the app quits
        }
    }

    @Test
    fun navBar_backFromAnyDestination_returnsToHomeFeed() {
        composeTestRule.apply {
            // GIVEN the user navigated to the Search destination
            onNodeWithText(search).performClick()
            // WHEN the user uses the system button/gesture to go back,
            Espresso.pressBack()
            // THEN the app shows the Home Feed destination
            onNodeWithText(home).assertExists()
        }
    }

    @Test
    fun navBar_onClickSearch_navigatesToSearchScreen() {
        composeTestRule.apply {
            onNodeWithText(search).performClick()
            onNodeWithTag("search_upload").assertIsDisplayed()
        }
    }
}
