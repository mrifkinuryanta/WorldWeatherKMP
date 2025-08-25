package id.mrn.services.data.source.cache

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createDriver(dbName: String): SqlDriver
}