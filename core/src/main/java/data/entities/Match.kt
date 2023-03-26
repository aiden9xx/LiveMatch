package data.entities


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("matches")
    val matches: Matches?
)

data class Matches(
    @SerializedName("previous")
    val previous: List<Previous>,
    @SerializedName("upcoming")
    val upcoming: List<Upcoming>
)

data class Previous(
    @SerializedName("away")
    val away: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("highlights")
    val highlights: String?,
    @SerializedName("home")
    val home: String?,
    @SerializedName("winner")
    val winner: String?,
    @SerializedName("description")
    val description: String? = null
) : MatchData()

data class Upcoming(
    @SerializedName("away")
    val away: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("home")
    val home: String?,
    @SerializedName("description")
    val description: String? = null,
    var day: String? = "",
) : MatchData()

open class MatchData {

}