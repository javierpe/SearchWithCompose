package com.app.ml.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.ml.R
import com.app.ml.data.models.ActionScreen
import com.app.ml.ui.components.LoaderView
import com.app.ml.ui.components.SearchErrorAlertView
import com.app.ml.ui.components.SearchInfoAlertView
import com.app.ml.ui.components.SearchView
import com.app.ml.ui.theme.MLTheme
import com.app.ml.viewModels.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val action by viewModel.action.collectAsState()
    val recentSearch by viewModel.recentSearches.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            Column {
                val modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp)

                SearchView(
                    modifier = modifier
                        .fillMaxWidth(),
                    placeholder = stringResource(R.string.home_search),
                    onValueChanged = {
                        viewModel.loadRecentSearch(it)
                    }
                ) {
                    keyboardController?.hide()
                    viewModel.search(it)
                }

                recentSearch.takeIf { it.isNotEmpty() }?.let { list ->
                    RecentSearchScreen(
                        modifier = modifier,
                        list = list,
                    ) {
                        keyboardController?.hide()
                        viewModel.search(it)
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
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
                    SkeletonsScreen(
                        products = (action as ActionScreen.SkeletonAction).data
                    )
                }
                is ActionScreen.SuccessAction -> {
                    ProductListScreen(
                        modifier = Modifier.fillMaxSize(),
                        products = (action as ActionScreen.SuccessAction).responseModel.products
                    )
                }
                is ActionScreen.Undefined -> {
                    SearchInfoAlertView(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MLTheme {
        SearchScreen()
    }
}