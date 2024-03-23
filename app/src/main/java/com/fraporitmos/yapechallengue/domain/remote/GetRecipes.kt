package com.fraporitmos.yapechallengue.domain.remote

import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.data.remote.repository.ApplicationRemoteRepository
import javax.inject.Inject

class GetRecipes @Inject constructor(
    private val applicationRemoteRepository: ApplicationRemoteRepository
) {
    suspend operator fun invoke() : List<Recipe>{
        return applicationRemoteRepository.getRecipes()
    }
}