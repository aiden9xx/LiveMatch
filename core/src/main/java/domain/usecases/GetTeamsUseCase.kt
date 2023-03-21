package domain.usecases

import javax.inject.Inject

class GetTeamsUseCase @Inject constructor () : BaseUseCase() {
    suspend operator fun invoke() = teamRepository.getTeams()
}