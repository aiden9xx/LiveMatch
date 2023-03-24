package data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import data.entities.Team.Companion.TABLE_TEAM
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_TEAM)
@TypeConverters(
    SourceConverter::class
)
data class Team(
    var id: String?,
    var name: String?,
    var logo: String?,
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var teamId: Int = 0

    companion object {
        const val TABLE_TEAM = "table_team"
    }
}