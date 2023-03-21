package data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.entities.Team
import data.local.dao.TeamDao

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
/**
 * ArticleDatabase provides DAO [TeamDao] by using method [getArticleDao].
 */
@Database(
    entities = [Team::class],
    version = DbMigration.DB_VERSION
)
abstract class TeamDatabase : RoomDatabase() {

    abstract fun getArticleDao(): TeamDao

    companion object {
        private const val DB_NAME = "database_teams"

        @Volatile
        private var INSTANCE: TeamDatabase? = null

        fun getInstance(context: Context): TeamDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DbMigration.MIGRATION_TEAM)
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
