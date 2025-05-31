package id.mrn.services.cache

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createDriver(dbName: String): SqlDriver
}