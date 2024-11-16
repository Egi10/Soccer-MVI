package com.bajapuik.soccer_mvi.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bajapuik.soccer_mvi.db.SoccerDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = SoccerDatabase.Schema,
            context = context,
            name = DATABASE_NAME
        )
    }
}