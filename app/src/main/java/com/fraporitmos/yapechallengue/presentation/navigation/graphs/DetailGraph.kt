package com.fraporitmos.yapechallengue.presentation.navigation.graphs

import android.content.Intent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fraporitmos.yapechallengue.Utils.fromJson
import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.presentation.main.viewModel.RecipeViewModel
import com.fraporitmos.yapechallengue.presentation.navigation.routes.BaseRoutes
import com.fraporitmos.yapechallengue.presentation.navigation.routes.DetailsRoutes
import com.fraporitmos.yapechallengue.presentation.screens.DetailScreen.DetailScreen
import com.fraporitmos.yapechallengue.presentation.screens.MapScreen.MapScreen
import com.fraporitmos.yapechallengue.presentation.screens.MapScreen.MarkerInfo

fun NavGraphBuilder.detailsNavGraph(
    recipesViewModel: RecipeViewModel,
    navController: NavHostController,

    ) {
    navigation(
        route = BaseRoutes.DETAIL,
        startDestination = DetailsRoutes.DetailScreen.route
    ) {
        composable(DetailsRoutes.DetailScreen.route) { backStackEntry ->
            val intent: Intent? =
                backStackEntry.arguments?.get("android-support-nav:controller:deepLinkIntent") as? Intent
            val recipeJson: String? = intent?.data?.getQueryParameter("recipe")

            recipeJson?.let { json ->
                val recipe: Recipe = json.fromJson(Recipe::class.java)
                DetailScreen(recipe, navController)
            }
        }
        composable("${DetailsRoutes.MapScreen.route}/{infoMarker}") { backStackEntry ->
            val infoMarkerString: String? = backStackEntry.arguments?.getString("infoMarker")
            infoMarkerString?.let { json ->
                val markerInfo: MarkerInfo = json.fromJson(MarkerInfo::class.java)
                MapScreen(markerInfo, navController)
            }
        }

    }
}
