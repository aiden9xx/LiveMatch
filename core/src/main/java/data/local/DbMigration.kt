package data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import data.entities.Team

/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object DbMigration {
    const val DB_VERSION = 5

    val MIGRATION_TEAM: Array<Migration>
        get() = arrayOf(
            migrationTeam()
        )

    private fun migrationTeam(): Migration = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Team.TABLE_TEAM} ADD COLUMN body TEXT")
        }
    }
}

