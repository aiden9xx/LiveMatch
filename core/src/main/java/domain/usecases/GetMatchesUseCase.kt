package domain.usecases

import data.entities.Match
import data.remote.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor () : BaseUseCase() {
    operator fun invoke() : Flow<Resource<Match>> = flow {
        emit(Resource.Loading())
        emit(matchRepository.getMatches())
    }
}