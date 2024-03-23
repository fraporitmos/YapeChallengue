package com.fraporitmos.yapechallengue.framework.persist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fraporitmos.yapechallengue.framework.persist.db.entities.RecipeEntity

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: RecipeEntity)

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): List<RecipeEntity>

}