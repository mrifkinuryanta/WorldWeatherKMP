package id.mrn.services.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(dbName: String): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, dbName)
    }
}