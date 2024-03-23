package com.fraporitmos.yapechallengue.presentation.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.fraporitmos.yapechallengue.presentation.navigation.routes.BottombarRoutes


var BottomBarItems = listOf(
    BarItem(
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        BottombarRoutes.HomeScreen.route
    ),
    BarItem(
        "Saved",
        Icons.Filled.Save,
        Icons.Default.Save,
        BottombarRoutes.SaveScreen.route
    )

)
val InnerScreens = listOf(
    BottombarRoutes.HomeScreen,
    BottombarRoutes.SaveScreen
)
data class BarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

