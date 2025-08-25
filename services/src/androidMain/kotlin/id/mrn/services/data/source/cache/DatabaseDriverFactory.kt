package id.mrn.services.data.source.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import id.mrn.services.cache.ServicesDatabase

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(dbName: String): SqlDriver {
        return AndroidSqliteDriver(ServicesDatabase.Schema, context, dbName)
    }
}