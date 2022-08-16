package com.app.ml.presentation.viewModels

import app.cash.turbine.test
import com.app.ml.searchProduct.data.api.useCases.SearchUseCase
import com.app.ml.data.models.ActionScreen
import com.app.ml.searchProduct.data.models.ProductSearchResponseModel
import com.app.ml.database.entities.RecentSearchEntity
import com.app.ml.searchProduct.presentation.viewModels.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @Mock
    lateinit var searchUseCase: SearchUseCase

    @Mock
    lateinit var errorAction: ActionScreen.ErrorAction<ProductSearchResponseModel>

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        searchViewModel = SearchViewModel(
            searchUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `action property should be have a value when search a product`() = runTest {
        whenever(searchUseCase.search(String())).thenReturn(flow { emit(errorAction) })
        searchViewModel.search(String())
        searchViewModel.action.test {
            awaitItem()
            assert(awaitItem() is ActionScreen.ErrorAction)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `recentSearches property should be empty when search a product`() = runTest {
        whenever(searchUseCase.search(String())).thenReturn(flow { emit(errorAction) })
        searchViewModel.search(String())
        searchViewModel.recentSearches.test {
            assert(awaitItem().isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `recentSearches property should be empty list when loadRecentSearch value is empty`() = runTest {
        searchViewModel.loadRecentSearch(String())
        searchViewModel.recentSearches.test {
            assert(awaitItem().isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `recentSearches property should not be empty list when loadRecentSearch value is valid`() = runTest {
        whenever(searchUseCase.loadRecentSearchByValue("Samsung")).thenReturn(
            listOf(
                RecentSearchEntity("Samsung")
            )
        )
        searchViewModel.loadRecentSearch("Samsung")
        searchViewModel.recentSearches.test {
            awaitItem()
            assert(awaitItem().isNotEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }
}