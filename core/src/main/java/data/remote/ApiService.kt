package data.remote

import data.entities.Match
import domain.entities.Teams
import retrofit2.Response
import retrofit2.http.*
import com.haroldadmin.cnradapter.NetworkResponse

/**
 * Service to fetch data using endpoint [API_URL].
 */
interface ApiService {

    @GET("teams")
    suspend fun getTeams(): Response<Teams>

    @GET("teams/{teamId}/matches")
    suspend fun getTeamMatches(@Path("teamId") teamId: String): NetworkResponse<Match, ErrorResponse>

    @GET("teams/matches")
    suspend fun getMatches(): NetworkResponse<Match, ErrorResponse>

    companion object {
        const val API_URL = "https://jmde6xvjr4.execute-api.us-east-1.amazonaws.com/"
    }
}