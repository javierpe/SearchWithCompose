package com.app.ml.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.app.ml.data.ProductImageMode
import com.app.ml.data.models.productDetail.PictureItemModel
import com.app.ml.ui.components.ProductImage
import com.app.ml.ui.theme.MLTheme
import org.junit.Rule
import org.junit.Test

class ProductImageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productShouldBeSingleWithDefaultImage() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id"
                )
            }
        }
        composeTestRule
            .onNodeWithTag("product_single_default_image")
            .assertExists()
    }

    @Test
    fun productShouldBeSingleWithThumbnail() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id",
                    thumbnail = "url"
                )
            }
        }

        composeTestRule
            .onNodeWithTag("product_single_image")
            .assertExists()

        composeTestRule
            .onNodeWithTag("product_slider_container")
            .assertDoesNotExist()
    }

    @Test
    fun productSliderContainerShouldNotShowWhenModeIsSingle() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id",
                    thumbnail = "url"
                )
            }
        }

        composeTestRule
            .onNodeWithTag("product_slider_container")
            .assertDoesNotExist()
    }

    @Test
    fun productWithSliderShouldHaveAContainer() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id",
                    mode = ProductImageMode.PRODUCT_SLIDER
                )
            }
        }

        composeTestRule
            .onNodeWithTag("product_slider_container")
            .assertExists()
    }

    @Test
    fun productWithSliderShouldHaveHorizontalPager() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id",
                    mode = ProductImageMode.PRODUCT_SLIDER
                )
            }
        }

        composeTestRule
            .onNodeWithTag("product_slider_content")
            .assertExists()
    }

    @Test
    fun productWithSliderShouldHaveHorizontalPageIndicator() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id",
                    mode = ProductImageMode.PRODUCT_SLIDER
                )
            }
        }

        composeTestRule
            .onNodeWithTag("product_slider_content_indicator")
            .assertExists()
    }

    @Test
    fun productWithSliderAndImagesShouldHaveSliderContentImage() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id",
                    mode = ProductImageMode.PRODUCT_SLIDER,
                    images = listOf(PictureItemModel(String(), String()))
                )
            }
        }

        composeTestRule
            .onNodeWithTag("product_slider_content_image")
            .assertExists()
    }

    @Test
    fun productWithSliderWithoutImagesShouldHaveNotSliderContentImage() {
        composeTestRule.setContent {
            MLTheme {
                ProductImage(
                    id = "product_id",
                    mode = ProductImageMode.PRODUCT_SLIDER
                )
            }
        }

        composeTestRule
            .onNodeWithTag("product_slider_content_image")
            .assertDoesNotExist()
    }
}