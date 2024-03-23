package com.fraporitmos.yapechallengue.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fraporitmos.yapechallengue.presentation.main.viewModel.RecipeViewModel
import com.fraporitmos.yapechallengue.presentation.navigation.routes.BottombarRoutes
import com.fraporitmos.yapechallengue.presentation.navigation.routes.BaseRoutes
import com.fraporitmos.yapechallengue.presentation.screens.HomeScreen.HomeScreen
import com.fraporitmos.yapechallengue.presentation.screens.SaveScreen.SaveScreen

@Composable
fun MenuNavGraph(
    recipesViewModel: RecipeViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = BaseRoutes.BOTTOM_BAR,
        startDestination = BottombarRoutes.HomeScreen.route
    ) {
        composable(
            route = BottombarRoutes.HomeScreen.route,
            enterTransition = null,
            exitTransition = null
        ) {
            HomeScreen(recipesViewModel,navController)
        }
        composable(
            route = BottombarRoutes.SaveScreen.route,
            enterTransition = null,
            exitTransition = null
        ) {
            SaveScreen(
                navController
            )
        }
        detailsNavGraph(recipesViewModel,navController)
    }
}
