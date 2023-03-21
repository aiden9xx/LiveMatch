package domain.usecases

import domain.repositories.MatchRepository
import domain.repositories.TeamDataRepository
import javax.inject.Inject

open class BaseUseCase {
    @Inject lateinit var teamRepository: TeamDataRepository
    @Inject lateinit var matchRepository: MatchRepository
}