package com.app.ml.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ml.api.useCases.SearchUseCase
import com.app.ml.data.models.ActionScreen
import com.app.ml.data.models.search.ProductSearchResponseModel
import com.app.ml.db.entities.RecentSearchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _recentSearches = MutableStateFlow<List<RecentSearchEntity>>(emptyList())
    val recentSearches: StateFlow<List<RecentSearchEntity>> = _recentSearches

    private val _action = MutableStateFlow<ActionScreen<ProductSearchResponseModel>>(ActionScreen.Undefined())
    val action: StateFlow<ActionScreen<ProductSearchResponseModel>> = _action

    fun search(value: String) {
        _recentSearches.value = emptyList()
        viewModelScope.launch {
            searchUseCase.search(value).collect {
                _action.value = it
            }
        }
    }

    fun loadRecentSearch(value: String) {
        if (value.isNotEmpty()) {
            viewModelScope.launch {
                searchUseCase.loadRecentSearchByValue(value).takeIf { it.isNotEmpty() }?.let {
                    _recentSearches.value = it
                }
            }
        } else {
            _recentSearches.value = emptyList()
        }
    }
}