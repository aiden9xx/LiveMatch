package domain.repositories

import data.entities.Team
import data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface TeamDataRepository {
    fun getTeams(): Flow<Resource<List<Team>>>
    fun clearTeams(): Flow<Resource<List<Team>>>
}
