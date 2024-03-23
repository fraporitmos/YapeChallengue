package com.fraporitmos.yapechallengue.domain.remote

import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.data.remote.repository.ApplicationRemoteRepository
import java.util.Locale
import javax.inject.Inject

class SearchRecipe @Inject constructor(
    private val applicationRemoteRepository: ApplicationRemoteRepository
) {
    suspend operator fun invoke(query: String): List<Recipe> {
        val normalizedQuery = query.toLowerCase(Locale.ROOT)
        return applicationRemoteRepository.getRecipes()
            .filter {
                it.name.toLowerCase(Locale.ROOT).contains(normalizedQuery) ||
                        it.ingredients.any {
                            ingredient ->
                            ingredient.toLowerCase(Locale.ROOT).contains(normalizedQuery)
                        }
            }
    }
}