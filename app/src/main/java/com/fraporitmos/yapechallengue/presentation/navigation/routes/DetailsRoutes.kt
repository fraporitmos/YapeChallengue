package com.fraporitmos.yapechallengue.presentation.navigation.routes

sealed class DetailsRoutes(val route: String) {
    object DetailScreen : DetailsRoutes(route = "DetailScreen")
    object MapScreen : DetailsRoutes(route = "MapScreen")

}