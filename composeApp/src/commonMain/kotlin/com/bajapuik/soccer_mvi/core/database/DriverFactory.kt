package com.bajapuik.soccer_mvi.core.database

import app.cash.sqldelight.db.SqlDriver

const val DATABASE_NAME = "soccermvi.db"

expect class DriverFactory {
    fun createDriver(): SqlDriver
}