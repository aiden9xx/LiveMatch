package data.repositories

import android.content.Context
import com.haroldadmin.cnradapter.NetworkResponse
import data.entities.Match
import domain.repositories.MatchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import data.remote.ApiService
import data.remote.Resource
import extension.timeoutConnection
import extension.unknownError

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
@ExperimentalCoroutinesApi
class MatchDataSourceImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val service: ApiService
) : MatchRepository {
    override suspend fun getTeamDetail(teamId: String): Resource<Match> {
        return when (val res = service.getTeamMatches(teamId)) {
            is NetworkResponse.Success -> Resource.Success(data = res.body)
            is NetworkResponse.NetworkError -> Resource.Error(message = context.timeoutConnection)
            is NetworkResponse.ServerError -> Resource.Error(
                message = context.unknownError
            )
            else -> Resource.Error(message = context.unknownError)
        }
    }

    override suspend fun getMatches(): Resource<Match> {
        return when (val res = service.getMatches()) {
            is NetworkResponse.Success -> Resource.Success(data = res.body)
            is NetworkResponse.NetworkError -> Resource.Error(message = context.timeoutConnection)
            is NetworkResponse.ServerError -> Resource.Error(
                message = context.unknownError
            )
            else -> Resource.Error(message = context.unknownError)
        }
    }
}