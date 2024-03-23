package com.fraporitmos.yapechallengue.core.entities


data class Recipe(
    val description: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val latitude: String,
    val longitude: String,
    val name: String,
    val origin: String,
    val preparation: String,
    val region: String,
    val id: Int
)