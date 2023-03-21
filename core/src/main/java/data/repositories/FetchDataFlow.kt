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

        emit(Resource.Success(fetchNewsFromLocalDatabase().first()))

        val apiResponse = fetchNewsFromServer()
        val remoteArticle = apiResponse.body()

        if (apiResponse.isSuccessful && remoteArticle != null) {
            storeNewsToLocalDatabase(remoteArticle)
        } else {
            emit(Resource.Error(apiResponse.message()))
        }

        emitAll(
            fetchNewsFromLocalDatabase().map {
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
    protected abstract suspend fun storeNewsToLocalDatabase(response: REQUEST)

    /**
     * Fetch news from local database
     */
    @MainThread
    protected abstract fun fetchNewsFromLocalDatabase(): Flow<RESULT>

    /**
     * Fetch news from the server
     */
    @MainThread
    protected abstract suspend fun fetchNewsFromServer(): Response<REQUEST>
}
