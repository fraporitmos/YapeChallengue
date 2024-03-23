package com.fraporitmos.yapechallengue.data.persist.repository

import android.content.Context
import com.fraporitmos.yapechallengue.framework.persist.db.DatabaseService
import com.fraporitmos.yapechallengue.framework.persist.db.entities.RecipeEntity
import com.fraporitmos.yapechallengue.core.entities.Recipe


class RoomPersistDataSource(context: Context): ApplicationPersistentDataSource {
    val appDao  = DatabaseService.getInstance(context).getDao()

    override suspend fun addRecipe(recipe: Recipe)  =
        appDao.addRecipe(RecipeEntity.fromRecipe(recipe))

    override suspend fun deleteRecipe(recipe: Recipe)  =
        appDao.deleteRecipe(RecipeEntity.fromRecipe(recipe))

    override suspend fun getAllRecipes(): List<Recipe> =
        appDao.getAllRecipes().map { it.toRecipe() }
}