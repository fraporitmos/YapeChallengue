package com.fraporitmos.yapechallengue.domain.persist

import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.data.persist.repository.ApplicationPersistRepository
import javax.inject.Inject

class SaveOfflineRecipe  @Inject constructor(
    private val applicationRemoteRepository: ApplicationPersistRepository
) {
    suspend operator fun invoke(recipe: Recipe){
        return applicationRemoteRepository.addRecipe(recipe)
    }
}