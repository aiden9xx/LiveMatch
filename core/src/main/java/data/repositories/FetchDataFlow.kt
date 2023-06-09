package data.repositories

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import data.remote.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
/**
 * [RESULT] Type for database.
 * [REQUEST] Type for network.
 */
@ExperimentalCoroutinesApi
abstract class FetchDataFlow<RESULT, REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {

        Log.d("hailt", " emit local data")
        emit(Resource.Success(fetchTeamsFromLocalDatabase().first()))

        val apiResponse = fetchTeamsFromServer()
        val remoteTeams = apiResponse.body()

        if (apiResponse.isSuccessful && remoteTeams != null) {
            Log.d("hailt", " storeTeamsToLocalDatabase")
            storeTeamsToLocalDatabase(remoteTeams)
        } else {
            emit(Resource.Error(apiResponse.message()))
        }

        Log.d("hailt", " emit final data")
        emitAll(
            fetchTeamsFromLocalDatabase().map {
                Resource.Success<RESULT>(it)
            }
        )
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Error("Network error"))
    }

    /**
     * Store teams to the local database
     */
    @WorkerThread
    protected abstract suspend fun storeTeamsToLocalDatabase(response: REQUEST)

    /**
     * Fetch teams from local database
     */
    @MainThread
    protected abstract fun fetchTeamsFromLocalDatabase(): Flow<RESULT>

    /**
     * Fetch teams from the server
     */
    @MainThread
    protected abstract suspend fun fetchTeamsFromServer(): Response<REQUEST>
}
