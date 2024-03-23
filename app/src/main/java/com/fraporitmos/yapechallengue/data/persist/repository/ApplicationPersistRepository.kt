package com.fraporitmos.yapechallengue.data.persist.repository

import com.fraporitmos.yapechallengue.core.entities.Recipe

class ApplicationPersistRepository(private val applicationPersistentRepository: ApplicationPersistentDataSource) {
    suspend fun addRecipe(recipe: Recipe) = applicationPersistentRepository.addRecipe(recipe)
    suspend fun getAllRecipes() = applicationPersistentRepository.getAllRecipes()
}