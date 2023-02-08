package com.pujol.chucknorrisjokes.presentation.category.list

sealed class CategoriesEvent {
    data class OnNavigateToCategoryDetail(val category: String) : CategoriesEvent()
    object OnNavigateUp : CategoriesEvent()
    object GetCategories : CategoriesEvent()
}
