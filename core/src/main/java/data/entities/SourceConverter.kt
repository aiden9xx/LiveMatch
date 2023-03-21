package data.entities

import android.os.Parcelable
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

class SourceConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toBoilSource(data: String?): Source {
            if (data == null) {
                return Source("empty", "empty")
            }
            val listType = object : TypeToken<Source>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromBoilSource(data: Source): String {
            return gson.toJson(data)
        }
    }
}

@Parcelize
data class Source(
    var id: String?,
    var name: String?
) : Parcelable