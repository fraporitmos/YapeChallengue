package com.fraporitmos.yapechallengue.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.fraporitmos.yapechallengue.presentation.theme.AppTheme
import com.fraporitmos.yapechallengue.presentation.main.viewModel.RecipeViewModel
import com.fraporitmos.yapechallengue.presentation.navigation.graphs.RootNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val recipesViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        recipesViewModel.getRecipes()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme{
                RootNavigationGraph(
                    recipesViewModel,
                    navController = rememberNavController()
                )
            }
        }
    }
}

