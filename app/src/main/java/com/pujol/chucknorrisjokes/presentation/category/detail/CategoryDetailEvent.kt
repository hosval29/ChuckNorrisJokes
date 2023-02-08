package com.pujol.chucknorrisjokes.presentation.category.detail

sealed class CategoryDetailEvent {
    object OnNavigateUp : CategoryDetailEvent()
    data class GetCategoryDetail (val category: String): CategoryDetailEvent()
}
