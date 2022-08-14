package com.app.ml.ui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.app.ml.ui.components.BuyButton
import com.app.ml.ui.theme.MLTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class BuyButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    lateinit var onClick: () -> Unit

    @Before
    fun setUp() {
        // Start the app
        composeTestRule.setContent {
            MLTheme {
                BuyButton(text = "Text", onClick = onClick)
            }
        }
    }

    @Test
    fun buyButtonShouldHaveClickAction() {
        composeTestRule
            .onNodeWithTag("buy_button")
            .assert(hasClickAction())
    }

    @Test
    fun buyButtonShouldHaveText() {
        composeTestRule
            .onNodeWithTag("buy_button_text", useUnmergedTree = true)
            .assert(hasText("Text"))
    }

    @Test
    fun buyClickShouldBeInvoked() {
        composeTestRule
            .onNodeWithTag("buy_button")
            .performClick()

        verify(onClick).invoke()
    }
}