package com.fraporitmos.yapechallengue.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fraporitmos.yapechallengue.presentation.main.viewModel.RecipeViewModel
import com.fraporitmos.yapechallengue.presentation.navigation.bottombar.BottomBarScreen
import com.fraporitmos.yapechallengue.presentation.navigation.routes.BaseRoutes

@Composable
fun RootNavigationGraph(
    recipeViewModel: RecipeViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = BaseRoutes.ROOT,
        startDestination =  BaseRoutes.BOTTOM_BAR
    ) {
        composable(route = BaseRoutes.BOTTOM_BAR) {
            BottomBarScreen(recipeViewModel)
        }
        detailsNavGraph(recipeViewModel,navController)
    }
}

