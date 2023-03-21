package domain.usecases

import javax.inject.Inject

class RemoveTeamsUseCase @Inject constructor () : BaseUseCase() {
    suspend operator fun invoke() = teamRepository.clearTeams()
}