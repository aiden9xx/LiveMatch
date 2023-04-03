package data.repositories

import data.entities.Team
import data.local.dao.TeamDao
import data.remote.ApiService
import data.remote.Resource
import domain.entities.Teams
import domain.repositories.TeamDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
@ExperimentalCoroutinesApi
class TeamDataSourceImpl @Inject constructor(
    private val dao: TeamDao,
    private val service: ApiService
) : TeamDataRepository {

    /**
     * Fetching data from the server and store to the local database then emit the local database
     */
    override fun getTeams(): Flow<Resource<List<Team>>> {
        return object : FetchDataFlow<List<Team>, Teams>() {

            override suspend fun storeTeamsToLocalDatabase(response: Teams) = dao.addTeams(response.teams)

            override fun fetchTeamsFromLocalDatabase(): Flow<List<Team>> = dao.getAllTeams()

            override suspend fun fetchTeamsFromServer(): Response<Teams> = service.getTeams()

        }.asFlow()
    }
}