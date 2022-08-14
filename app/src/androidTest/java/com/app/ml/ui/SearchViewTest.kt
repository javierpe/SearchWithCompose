package com.app.ml.ui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.app.ml.ui.components.SearchView
import com.app.ml.ui.theme.MLTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SearchViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    lateinit var onValueChanged: (String) -> Unit

    @Mock
    lateinit var onSearch: (String) -> Unit

    private val tagView = "search_input"
    private val placeholder = "Search"

    @Test
    fun searchViewShouldHaveSearchInput() {

        composeTestRule.setContent {
            MLTheme {
                SearchView(
                    onValueChanged = onValueChanged,
                    onSearch = onSearch,
                    placeholder = placeholder
                )
            }
        }

        composeTestRule
            .onNodeWithTag(tagView)
            .assertExists()
    }

    @Test
    fun searchViewShouldHavePlaceholder() {

        composeTestRule.setContent {
            MLTheme {
                SearchView(
                    onValueChanged = onValueChanged,
                    onSearch = onSearch,
                    placeholder = placeholder
                )
            }
        }

        composeTestRule
            .onNodeWithTag("search_placeholder", useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun searchViewWithoutPlaceholderShouldNotShow() {

        composeTestRule.setContent {
            MLTheme {
                SearchView(
                    onValueChanged = onValueChanged,
                    onSearch = onSearch
                )
            }
        }

        composeTestRule
            .onNodeWithTag("search_placeholder", useUnmergedTree = true)
            .assertDoesNotExist()
    }

    @Test
    fun searchViewWithPlaceholderShouldHaveText() {

        composeTestRule.setContent {
            MLTheme {
                SearchView(
                    onValueChanged = onValueChanged,
                    onSearch = onSearch,
                    placeholder = placeholder
                )
            }
        }

        composeTestRule
            .onNodeWithTag("search_placeholder", useUnmergedTree = true)
            .assert(hasText(placeholder))
    }

    @Test
    fun searchViewShouldChangeValueWhenPerformInput() {

        composeTestRule.setContent {
            MLTheme {
                SearchView(
                    onValueChanged = onValueChanged,
                    onSearch = onSearch
                )
            }
        }

        composeTestRule
            .onNodeWithTag(tagView)
            .performTextInput("Text input")

        verify(onValueChanged).invoke("Text input")
    }

    @Test
    fun searchViewShouldHaveIcon() {

        composeTestRule.setContent {
            MLTheme {
                SearchView(
                    onValueChanged = onValueChanged,
                    onSearch = onSearch
                )
            }
        }

        composeTestRule
            .onNodeWithTag("search_icon", useUnmergedTree = true)
            .assertContentDescriptionEquals("Search")
            .assertExists()

    }

    @Test
    fun searchViewValueStateShouldChangeWithInput() {
        composeTestRule.setContent {
            MLTheme {
                SearchView(
                    onValueChanged = onValueChanged,
                    onSearch = onSearch
                )
            }
        }

        composeTestRule
            .onNodeWithTag(tagView)
            .performTextInput("Text input")

        composeTestRule.onNodeWithText("").assertDoesNotExist()
        composeTestRule.onNodeWithText("Text input").assertExists()
    }

}