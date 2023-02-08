package com.pujol.chucknorrisjokes.presentation.category.list

data class CategoriesState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val categories: List<String> = emptyList()
)