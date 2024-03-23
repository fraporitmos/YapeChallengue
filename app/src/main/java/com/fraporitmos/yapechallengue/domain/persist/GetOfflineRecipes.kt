package com.fraporitmos.yapechallengue.domain.persist

import com.fraporitmos.yapechallengue.data.persist.repository.ApplicationPersistRepository
import javax.inject.Inject

class GetOfflineRecipes @Inject constructor(
    private val applicationRemoteRepository: ApplicationPersistRepository
) {
    suspend operator fun invoke() =  applicationRemoteRepository.getAllRecipes().reversed()
}