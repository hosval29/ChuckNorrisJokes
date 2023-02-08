package com.pujol.chucknorrisjokes.presentation.category.detail

import com.pujol.chucknorrisjokes.domain.model.CategoryDetail

data class CategoryDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val categoryDetail: CategoryDetail = CategoryDetail()
)