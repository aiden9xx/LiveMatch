package data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.entities.Team
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
@Dao
interface TeamDao {
    /**
     * Inserts [teams] into the [Team.TABLE_TEAM] table.
     * @param teams
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTeams(teams: List<Team>)

    /**
     * Fetches all the data from the [Team.TABLE_TEAM] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Team.TABLE_TEAM}")
    fun getAllTeams(): Flow< List<Team>>

    /**
     * Remove all the data from the [Team.TABLE_TEAM] table.
     */
    @Query("DELETE FROM ${Team.TABLE_TEAM}")
    suspend fun deleteAllTeams()
}