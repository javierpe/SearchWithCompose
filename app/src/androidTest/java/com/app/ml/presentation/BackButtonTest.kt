package com.app.ml.presentation

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.app.ml.ui.designSystem.BackButton
import com.app.ml.ui.theme.MLTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class BackButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    lateinit var onClick: () -> Unit

    @Before
    fun setUp() {
        // Start the app
        composeTestRule.setContent {
            MLTheme {
                BackButton(
                    modifier = Modifier,
                    onBackPressed = onClick
                )
            }
        }
    }

    @Test
    fun buttonShouldHaveClickAction() {
        composeTestRule
            .onNodeWithTag("back_button")
            .assert(hasClickAction())
    }

    @Test
    fun buttonClickShouldBeInvoked() {
        composeTestRule
            .onNodeWithTag("back_button")
            .performClick()

        verify(onClick).invoke()
    }
}