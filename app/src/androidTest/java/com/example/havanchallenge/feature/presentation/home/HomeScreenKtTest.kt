package com.example.havanchallenge.feature.presentation.home

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.havanchallenge.core.ui.theme.HavanChallengeTheme
import com.example.havanchallenge.feature.domain.ProductState
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {
    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    lateinit var navHostControllerTest: TestNavHostController

    @Test
    fun initialState_isRendered() {
        testRule.setContent {
            navHostControllerTest = TestNavHostController(LocalContext.current)
            navHostControllerTest.navigatorProvider.addNavigator(ComposeNavigator())
            HavanChallengeTheme {
                HomeScreen(navHostController = navHostControllerTest)
                ProductState(
                    isLoading = true,
                    products = emptyList()
                )
            }
        }
        testRule.onNodeWithContentDescription(
            "Loading Indicator"
        ).assertIsDisplayed()
    }
}