package sample.aniwave

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import kotlin.properties.ReadOnlyProperty

class MainScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
        ReadOnlyProperty<Any?, String> { _, _ -> activity.getString(resId) }

    private val home by composeRule.stringResource(R.string.home)

    @Test
    fun firstScreen_isHomeFeed() {
        composeRule.apply {
            onNodeWithText(home).assertIsSelected()
        }
    }

    @Test
    fun navBar_bottomBarIsDisplayed() {
        composeRule.onNodeWithTag("AniWaveBottomBar").assertIsDisplayed()
    }
}
