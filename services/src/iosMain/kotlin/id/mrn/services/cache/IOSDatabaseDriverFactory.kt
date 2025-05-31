package id.mrn.services.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(dbName: String): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, dbName)
    }
}