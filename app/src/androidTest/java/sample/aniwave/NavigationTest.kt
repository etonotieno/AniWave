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
    private val upload by composeTestRule.stringResource(R.string.upload)

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
            // GIVEN the user navigates to the Upload destination
            onNodeWithText(upload).performClick()
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
            // GIVEN the user navigated to the Upload destination
            onNodeWithText(upload).performClick()
            // WHEN the user uses the system button/gesture to go back,
            Espresso.pressBack()
            // THEN the app shows the Home Feed destination
            onNodeWithText(home).assertExists()
        }
    }

    @Test
    fun navBar_onClickUpload_navigatesToUploadScreen() {
        composeTestRule.apply {
            onNodeWithText(upload).performClick()
            onNodeWithTag("search_upload").assertIsDisplayed()
        }
    }
}
