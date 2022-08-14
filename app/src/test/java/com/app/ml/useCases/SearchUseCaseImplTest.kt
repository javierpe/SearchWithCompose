package com.app.ml.useCases

import app.cash.turbine.test
import com.app.ml.api.repositories.SearchRepository
import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.search.ProductSearchResponseModel
import com.app.ml.db.AppDatabase
import com.app.ml.db.dao.RecentSearchDao
import com.app.ml.db.dao.SkeletonsDao
import com.app.ml.db.entities.RecentSearchEntity
import com.app.ml.db.entities.SkeletonsEntity
import com.app.ml.impl.CONTEXT_SEARCH
import com.app.ml.impl.SearchUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseImplTest {

    @Mock
    lateinit var searchRepository: SearchRepository

    @Mock
    lateinit var database: AppDatabase

    @Mock
    lateinit var skeletonsDao: SkeletonsDao

    @Mock
    lateinit var recentSearchDao: RecentSearchDao

    @Mock
    lateinit var errorAction: ActionScreen.ErrorAction<ProductSearchResponseModel>

    private val successAction by lazy {
        ActionScreen.SuccessAction(
            responseModel = ProductSearchResponseModel(String(), emptyList())
        )
    }

    private lateinit var searchUseCaseImpl: SearchUseCaseImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        searchUseCaseImpl = SearchUseCaseImpl(
            StandardTestDispatcher(),
            searchRepository,
            database
        )

        whenever(database.skeletonsDao()).thenReturn(skeletonsDao)
        whenever(database.recentSearchDao()).thenReturn(recentSearchDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `LoadingAction should return first`() = runTest {
        whenever(searchRepository.search(String())).thenReturn(flow { emit(errorAction) })
        assert(searchUseCaseImpl.search(String()).first() is ActionScreen.LoadingAction)
    }

    @Test
    fun `SkeletonAction should return when skeleton database have data`() = runTest {
        whenever(searchRepository.search(String())).thenReturn(flow { emit(successAction) })
        whenever(
            database.skeletonsDao().provideSkeletonsByContext(CONTEXT_SEARCH)
        ).thenReturn(SkeletonsEntity(CONTEXT_SEARCH, emptyList()))

        assert(searchUseCaseImpl.search(String()).first() is ActionScreen.SkeletonAction)
    }

    @Test
    fun `saveSkeletonsByContext should be called when response is success`() = runTest {
        whenever(searchRepository.search(String())).thenReturn(flow { emit(successAction) })
        searchUseCaseImpl.search(String()).collect()
        verify(database.skeletonsDao()).saveSkeletonsByContext(
            SkeletonsEntity(
                CONTEXT_SEARCH,
                emptyList()
            )
        )
    }

    @Test
    fun `addRecentSearchValue should be called when response is success`() = runTest {
        whenever(searchRepository.search(String())).thenReturn(flow { emit(successAction) })
        searchUseCaseImpl.search(String()).collect()
        verify(database.recentSearchDao()).addRecentSearchValue(
            RecentSearchEntity(String())
        )
    }

    @Suppress("TooGenericExceptionThrown")
    @Test
    fun `ErrorAction should be return when an exception happens in the repository`() = runTest {
        whenever(searchRepository.search(String())).thenReturn(
            flow { throw RuntimeException("Crash!") }
        )

        whenever(
            database.skeletonsDao().provideSkeletonsByContext(CONTEXT_SEARCH)
        ).thenReturn(SkeletonsEntity(CONTEXT_SEARCH, emptyList()))

        searchUseCaseImpl.search(String()).test {
            awaitItem()
            assert(awaitItem() is ActionScreen.ErrorAction)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loadRecentSearchByValue should be called`() = runTest {
        searchUseCaseImpl.loadRecentSearchByValue(String())
        verify(database.recentSearchDao()).loadRecentSearchByValue(String())
    }
}