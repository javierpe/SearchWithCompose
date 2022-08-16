package com.app.ml.ui.designSystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.app.ml.productDetail.data.models.ProductImageMode
import com.app.ml.productDetail.data.models.PictureItemModel
import com.app.ml.ui.theme.Error
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductImage(
    modifier: Modifier = Modifier,
    id: String,
    thumbnail: String? = null,
    images: List<PictureItemModel> = emptyList(),
    mode: ProductImageMode = ProductImageMode.PRODUCT_SINGLE
) {

    if (mode == ProductImageMode.PRODUCT_SLIDER) {
        val pagerState = rememberPagerState()
        Column(
            modifier = Modifier
                .testTag("product_slider_container")
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            HorizontalPager(
                count = images.size,
                state = pagerState,
                modifier = Modifier
                    .weight(4f)
                    .testTag("product_slider_content")
            ) { page ->
                ProductImage(
                    modifier = modifier
                        .testTag("product_slider_content_image"),
                    url = images[page].url,
                    id = id
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .testTag("product_slider_content_indicator"),
            )
        }
    } else {
        thumbnail?.let {
            ProductImage(
                modifier = modifier
                    .testTag("product_single_image")
                    .clip(RoundedCornerShape(16.dp)),
                url = it,
                id = id
            )
        } ?: run {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .testTag("product_single_default_image"),
                colorFilter = ColorFilter.tint(color = Error)
            )
        }

    }
}

@Composable
fun ProductImage(
    modifier: Modifier,
    url: String,
    id: String
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .background(Color.White),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = id,
        contentScale = ContentScale.Fit
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp)
                )
            }
            is AsyncImagePainter.State.Error -> {
                Image(
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                    modifier = Modifier.size(50.dp),
                    colorFilter = ColorFilter.tint(color = Error)
                )
            }
            else -> {
                SubcomposeAsyncImageContent()
            }
        }

    }
}