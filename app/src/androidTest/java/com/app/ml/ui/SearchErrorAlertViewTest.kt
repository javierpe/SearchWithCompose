package com.app.ml.ui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.app.ml.ui.components.SearchErrorAlertView
import com.app.ml.ui.theme.MLTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchErrorAlertViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // Start the app
        composeTestRule.setContent {
            MLTheme {
                SearchErrorAlertView(errorMessage = "Error")
            }
        }
    }

    @Test
    fun searchErrorMessageShouldHaveText() {
        composeTestRule
            .onNodeWithTag("search_error_message")
            .assert(hasText("Error"))
    }

    @Test
    fun searchErrorShouldHaveIcon() {
        composeTestRule
            .onNodeWithTag("search_error_icon")
            .assertExists(errorMessageOnFail = "Search error must have an icon!")
    }
}