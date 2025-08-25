package id.mrn.services.data.source.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import id.mrn.services.cache.ServicesDatabase

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(dbName: String): SqlDriver {
        return NativeSqliteDriver(ServicesDatabase.Schema, dbName)
    }
}