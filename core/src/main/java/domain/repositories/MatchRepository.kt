package domain.repositories

import data.entities.Match
import data.entities.Matches
import data.remote.Resource

interface MatchRepository {
    suspend fun getTeamDetail(teamId: String): Resource<Match>
    suspend fun getMatches(): Resource<Match>
}
