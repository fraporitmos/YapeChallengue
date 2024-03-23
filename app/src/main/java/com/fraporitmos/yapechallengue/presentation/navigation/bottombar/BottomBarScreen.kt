package com.fraporitmos.yapechallengue.presentation.navigation.bottombar

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fraporitmos.yapechallengue.presentation.main.viewModel.RecipeViewModel
import com.fraporitmos.yapechallengue.presentation.navigation.graphs.MenuNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBarScreen(
    recipesViewModel: RecipeViewModel,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomNavigator(navController = navController,) }
    ) {
        MenuNavGraph(recipesViewModel,navController = navController)
    }
}

@Composable
fun BottomNavigator(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = InnerScreens.any { it.route == currentDestination?.route }
    var selectedIndex by remember { mutableStateOf(0) }

    if (bottomBarDestination) {
        NavigationBar() {
            BottomBarItems.forEachIndexed { index, barItem ->
                val selected = selectedIndex == index
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        selectedIndex = index
                        navController.navigate(barItem.route)
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) barItem.selectedIcon else barItem.unselectedIcon,
                            contentDescription = barItem.title
                        )
                    },
                    label = {
                        Text(
                            text = barItem.title,
                            fontSize = 10.sp
                        )
                    }
                )
            }
        }
    }
}

