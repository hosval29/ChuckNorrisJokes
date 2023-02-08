package com.pujol.chucknorrisjokes.presentation.category.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pujol.chucknorrisjokes.core.util.UiEvent
import com.pujol.chucknorrisjokes.ui.component.Toolbar
import com.pujol.chucknorrisjokes.ui.component.category.CategoryItem
import com.pujol.chucknorrisjokes.ui.theme.Background

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoriesScreen(
    navController: NavHostController,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navController.navigate(event.route)
                }
                is UiEvent.NavigateUp -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(topBar = {
        Toolbar(description = "Lista de categorías", enabledBack = false, onBackPressed = {
            viewModel.onEvent(CategoriesEvent.OnNavigateUp)
        })
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
        ) {
            items(items = state.categories, itemContent = {
                CategoryItem(
                    modifier = Modifier.padding(top = 8.dp, end = 16.dp, start = 16.dp),
                    description = it,
                    onClick = { category ->
                        viewModel.onEvent(CategoriesEvent.OnNavigateToCategoryDetail(category))
                    })
            })
        }
    }

}
