package com.fraporitmos.yapechallengue.data.remote.repository

import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.data.remote.services.RecipeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApplicationRemoteRepository @Inject constructor (private val recipeApiService: RecipeApiService) {
    suspend fun getRecipes(): List<Recipe> {
        return withContext(Dispatchers.IO) {
             recipeApiService.getRecipes()
        }
    }
}