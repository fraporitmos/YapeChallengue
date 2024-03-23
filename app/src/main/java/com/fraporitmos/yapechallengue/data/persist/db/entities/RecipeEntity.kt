package com.fraporitmos.yapechallengue.framework.persist.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fraporitmos.yapechallengue.core.entities.Recipe

@Entity(tableName = "recipe")
data class RecipeEntity(
    val description: String,
    val imageUrl: String,
    val ingredients: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val origin: String,
    val preparation: String,
    val region: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
){
    companion object{
        fun fromRecipe(recipe: Recipe) =
            RecipeEntity(
                recipe.description,
                recipe.imageUrl,
                recipe.ingredients.toString(),
                recipe.latitude,
                recipe.longitude,
                recipe.name,
                recipe.origin,
                recipe.preparation,
                recipe.region,
                recipe.id.toLong(),
            )
    }
    fun toRecipe() = Recipe(
        description,
        imageUrl,
       ingredients.split(",").map { it.trim() }.toMutableList(),
        latitude,
        longitude,
        name,
        origin,
        preparation,
        region,
        id.toInt()
    )
}