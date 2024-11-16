package com.bajapuik.soccer_mvi.core.database

import com.bajapuik.soccer_mvi.db.SoccerDatabase

object DatabaseFactory {
    fun create(driverFactory: DriverFactory): SoccerDatabase {
        val driver = driverFactory.createDriver()

        return SoccerDatabase.invoke(
            driver = driver
        )
    }
}
