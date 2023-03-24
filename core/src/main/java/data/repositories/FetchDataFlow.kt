package data.repositories

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

        emit(Resource.Success(fetchTeamsFromLocalDatabase().first()))

        val apiResponse = fetchTeamsFromServer()
        val remoteArticle = apiResponse.body()

        if (apiResponse.isSuccessful && remoteArticle != null) {
            storeTeamsToLocalDatabase(remoteArticle)
        } else {
            emit(Resource.Error(apiResponse.message()))
        }

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
     * Store news to the local database
     */
    @WorkerThread
    protected abstract suspend fun storeTeamsToLocalDatabase(response: REQUEST)

    /**
     * Fetch news from local database
     */
    @MainThread
    protected abstract fun fetchTeamsFromLocalDatabase(): Flow<RESULT>

    /**
     * Fetch news from the server
     */
    @MainThread
    protected abstract suspend fun fetchTeamsFromServer(): Response<REQUEST>
}
