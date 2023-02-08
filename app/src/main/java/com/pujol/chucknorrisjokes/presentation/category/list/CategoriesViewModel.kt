package com.pujol.chucknorrisjokes.presentation.category.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pujol.chucknorrisjokes.core.util.Resource
import com.pujol.chucknorrisjokes.core.util.UiEvent
import com.pujol.chucknorrisjokes.domain.use_case.GetCategoriesUseCase
import com.pujol.chucknorrisjokes.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) :
    ViewModel() {

    var state by mutableStateOf(CategoriesState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(CategoriesEvent.GetCategories)
    }

    fun onEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.GetCategories -> {
                getCategories()
            }
            is CategoriesEvent.OnNavigateUp -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }
            is CategoriesEvent.OnNavigateToCategoryDetail -> {
                val route = Route.CATEGORY_DETAIL.replace("{category}", event.category)
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(route))
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            state = state.copy(categories = it)
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(isError = true)

                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = response.loading ?: false)
                    }
                }
            }
        }
    }

}