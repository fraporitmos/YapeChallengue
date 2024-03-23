package com.fraporitmos.yapechallengue.data.remote.services

import com.fraporitmos.yapechallengue.core.entities.Recipe
import retrofit2.http.GET
import retrofit2.http.Headers

interface RecipeApiService {
    @Headers("x-api-key: DrkHAF?P7j8zoCx4ozG!7MHiHk8cD#zs&")
    @GET("cooking-recipes")
    suspend fun getRecipes(): List<Recipe>
}