package com.fraporitmos.yapechallengue.data.persist.repository

import com.fraporitmos.yapechallengue.core.entities.Recipe

interface ApplicationPersistentDataSource {
    suspend fun addRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun getAllRecipes() : List<Recipe>

}