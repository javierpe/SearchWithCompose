package com.app.ml.impl

import com.app.ml.api.repositories.SearchRepository
import com.app.ml.api.useCases.SearchUseCase
import com.app.ml.db.AppDatabase
import com.app.ml.db.entities.SkeletonsEntity
import com.app.ml.di.qualifiers.IODispatcher
import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.search.ProductSearchResponseModel
import com.app.ml.db.entities.RecentSearchEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val CONTEXT_SEARCH = "SEARCH"
const val MAX_QUANTITY_SKELETONS = 10

class SearchUseCaseImpl @Inject constructor(
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository,
    private val database: AppDatabase
) : SearchUseCase {

    override suspend fun search(value: String): Flow<ActionScreen<ProductSearchResponseModel>> {

        return searchRepository.search(value).onEach {
            if (it is ActionScreen.SuccessAction<ProductSearchResponseModel>) {

                // Save skeletons
                database.skeletonsDao().saveSkeletonsByContext(
                    SkeletonsEntity(
                        context = CONTEXT_SEARCH,
                        renders = it.responseModel.products.map { product -> product.id }.take(MAX_QUANTITY_SKELETONS)
                    )
                )

                // Save recent search
                database.recentSearchDao().addRecentSearchValue(
                    RecentSearchEntity(value)
                )
            }
        }.onStart {
            val skeletonContext = database.skeletonsDao().provideSkeletonsByContext(CONTEXT_SEARCH)

            skeletonContext?.let {
                emit(ActionScreen.SkeletonAction(it.renders))
            } ?: kotlin.run {
                emit(ActionScreen.LoadingAction())
            }
        }.catch {
            emit(ActionScreen.ErrorAction(it))
        }.flowOn(coroutineDispatcher)
    }

    override suspend fun loadRecentSearchByValue(value: String): List<RecentSearchEntity> {
        return withContext(coroutineDispatcher) {
            database.recentSearchDao().loadRecentSearchByValue(value)
        }
    }
}