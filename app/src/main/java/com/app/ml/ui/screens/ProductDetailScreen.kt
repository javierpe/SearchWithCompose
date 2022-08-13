package com.app.ml.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.ml.R
import com.app.ml.data.models.ActionScreen
import com.app.ml.ui.ProductDetailContent
import com.app.ml.ui.components.BackButton
import com.app.ml.ui.components.LoaderView
import com.app.ml.ui.components.SearchErrorAlertView
import com.app.ml.ui.components.SearchInfoAlertView
import com.app.ml.viewModels.ProductDetailViewModel

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    productId : String,
) {

    val action by viewModel.action.collectAsState()

    LaunchedEffect(key1 = productId) {
        viewModel.load(productId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when (action) {
            is ActionScreen.LoadingAction -> {
                LoaderView(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is ActionScreen.ErrorAction -> {
                val error = (action as ActionScreen.ErrorAction)
                SearchErrorAlertView(
                    modifier = Modifier
                        .align(Alignment.Center),
                    errorMessage = error.exception?.message
                        ?: error.errorBody?.string()
                        ?: stringResource(R.string.search_unexpected_error)
                )
            }
            is ActionScreen.SkeletonAction -> {

            }
            is ActionScreen.SuccessAction -> {
                Box {
                    ProductDetailContent(
                        product = (action as ActionScreen.SuccessAction).responseModel
                    )

                    BackButton(
                        modifier = Modifier.padding(10.dp)
                    ) { viewModel.goToBack() }
                }
            }
            is ActionScreen.Undefined -> {
                SearchInfoAlertView(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewProductDetailScreen() {
}