package com.app.ml.repositories

import com.app.ml.searchProduct.data.api.network.SearchService
import com.app.ml.data.models.ActionScreen
import com.app.ml.logger.Logger
import com.app.ml.searchProduct.data.models.ProductSearchResponseModel
import com.app.ml.searchProduct.domain.impl.SearchRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryImplTest {

    @Mock
    lateinit var logger: Logger

    @Mock
    lateinit var searchService: SearchService

    private lateinit var searchRepositoryImpl: SearchRepositoryImpl

    @Before
    fun setUp() {
        searchRepositoryImpl = SearchRepositoryImpl(
            searchService,
            logger
        )
    }

    @Test
    fun `ErrorAction should return when response is not success`() = runTest {
        whenever(searchService.search(String())).thenReturn(
            Response.error(401, String().toResponseBody())
        )
        val result = searchRepositoryImpl.search(String()).first()
        assert(result is ActionScreen.ErrorAction)
    }

    @Test
    fun `SuccessAction should return when response is success`() = runTest {
        whenever(searchService.search(String())).thenReturn(
            Response.success(200, ProductSearchResponseModel(String(), emptyList()))
        )
        val result = searchRepositoryImpl.search(String()).first()
        assert(result is ActionScreen.SuccessAction)
    }
}