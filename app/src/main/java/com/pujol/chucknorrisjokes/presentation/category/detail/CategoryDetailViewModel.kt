package com.pujol.chucknorrisjokes.presentation.category.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pujol.chucknorrisjokes.core.util.Resource
import com.pujol.chucknorrisjokes.core.util.UiEvent
import com.pujol.chucknorrisjokes.domain.use_case.GetCategoryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(private val categoryDetailUseCase: GetCategoryDetailUseCase) :
    ViewModel() {

    var state by mutableStateOf(CategoryDetailState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun getCategoryDetail(category: String) {
        viewModelScope.launch {
            categoryDetailUseCase.invoke(category).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            state = state.copy(categoryDetail = it)
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

    fun onEvent(event: CategoryDetailEvent) {
        when (event) {
            is CategoryDetailEvent.GetCategoryDetail -> {
                getCategoryDetail(event.category)
            }
            is CategoryDetailEvent.OnNavigateUp -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }
        }
    }

}