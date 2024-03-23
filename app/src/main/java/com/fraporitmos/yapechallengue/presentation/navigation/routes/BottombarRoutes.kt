package com.fraporitmos.yapechallengue.presentation.navigation.routes

sealed class BottombarRoutes (val route: String){
    object HomeScreen : BottombarRoutes("HomeScreen")
    object SaveScreen : BottombarRoutes("SaveScreen")
}


