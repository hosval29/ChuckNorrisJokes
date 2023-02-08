package com.pujol.chucknorrisjokes.presentation.category.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pujol.chucknorrisjokes.R
import com.pujol.chucknorrisjokes.core.util.UiEvent
import com.pujol.chucknorrisjokes.ui.component.Toolbar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryDetailScreen(
    category: String,
    navController: NavHostController,
    viewModel: CategoryDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (state.categoryDetail.categories.isEmpty()) {
        viewModel.onEvent(CategoryDetailEvent.GetCategoryDetail(category))
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateUp -> {
                    navController.navigateUp()
                }
                else -> Unit
            }
        }
    }

    Scaffold(topBar = {
        Toolbar(description = "Detalle de categoría", onBackPressed = {
            viewModel.onEvent(CategoryDetailEvent.OnNavigateUp)
        })
    }, content = { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val horizontalCenterGuideLine = createGuidelineFromTop(0.45f)
            val (categoryImage, categoryName, categoryCreated, categoryDescription) = createRefs()
            GlideImage(
                model = R.drawable.chuck_norris,
                contentDescription = "IMAGE_CATEGORY",
                contentScale = ContentScale.Fit,
                modifier = Modifier.constrainAs(categoryImage) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(horizontalCenterGuideLine)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                })
            Text(
                "${state.categoryDetail.categories.firstOrNull()}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(categoryName) {
                    top.linkTo(horizontalCenterGuideLine, margin = 16.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                })
            Text(
                state.categoryDetail.createdAt,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(categoryCreated) {
                    top.linkTo(categoryName.top)
                    bottom.linkTo(categoryName.bottom)
                    end.linkTo(parent.end, margin = 24.dp)
                })
            Text(
                state.categoryDetail.value,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(categoryDescription) {
                    top.linkTo(categoryName.bottom, margin = 4.dp)
                    start.linkTo(categoryName.start)
                    end.linkTo(parent.end, margin = 24.dp)
                    width = Dimension.fillToConstraints
                })
        }
    })
}