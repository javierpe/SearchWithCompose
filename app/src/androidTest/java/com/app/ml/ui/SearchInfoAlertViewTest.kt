package com.app.ml.ui

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.app.ml.ui.components.SearchInfoAlertView
import com.app.ml.ui.theme.MLTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.app.ml.R

class SearchInfoAlertViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var infoAlertText: String

    @Before
    fun setUp() {
        // Start the app
        composeTestRule.setContent {

            infoAlertText = stringResource(id = R.string.search_info_alert)

            MLTheme {
                SearchInfoAlertView()
            }
        }
    }

    @Test
    fun searchInfoAlertMessageShouldHaveText() {
        composeTestRule
            .onNodeWithTag("search_info_alert_text")
            .assert(hasText(infoAlertText))
    }

    @Test
    fun searchInfoAlertShouldHaveIcon() {
        composeTestRule
            .onNodeWithTag("search_info_alert_icon")
            .assertExists(errorMessageOnFail = "Search info alert must have an icon!")
    }
}