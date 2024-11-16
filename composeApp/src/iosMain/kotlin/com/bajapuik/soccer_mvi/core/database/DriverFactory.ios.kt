package com.bajapuik.soccer_mvi.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.bajapuik.soccer_mvi.db.SoccerDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = SoccerDatabase.Schema,
            name = DATABASE_NAME
        )
    }
}