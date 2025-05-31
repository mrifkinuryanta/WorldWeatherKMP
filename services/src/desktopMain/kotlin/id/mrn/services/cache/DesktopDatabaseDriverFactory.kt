package id.mrn.services.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

class DesktopDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(dbName: String): SqlDriver {
        val dbFile = File(System.getProperty("user.home"), ".WorldWeather/$dbName")
        if (!dbFile.parentFile.exists()) {
            dbFile.parentFile.mkdirs()
        }
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${dbFile.absolutePath}")
        try {
            AppDatabase.Schema.create(driver)
        } catch (e: Exception) {
            println("Desktop driver: Schema creation failed or schema already exists: ${e.message}")
        }
        return driver
    }
}