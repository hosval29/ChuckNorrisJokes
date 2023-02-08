package com.pujol.chucknorrisjokes.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pujol.chucknorrisjokes.presentation.category.list.CategoriesScreen
import com.pujol.chucknorrisjokes.presentation.category.detail.CategoryDetailScreen
import com.pujol.chucknorrisjokes.ui.navigation.Route
import com.pujol.chucknorrisjokes.ui.theme.ChuckNorrisJokesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChuckNorrisJokesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberAnimatedNavController()

                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AnimatedNavHost(
                            navController = navController,
                            startDestination = Route.CATEGORIES,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable(Route.CATEGORIES) {
                                CategoriesScreen(navController = navController)
                            }
                            composable(Route.CATEGORY_DETAIL) { backStackEntry ->
                                backStackEntry.arguments?.getString("category")?.let { category ->
                                    CategoryDetailScreen(category = category, navController = navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}