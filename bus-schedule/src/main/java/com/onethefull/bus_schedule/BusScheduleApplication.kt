package com.onethefull.bus_schedule

import android.app.Application
import com.onethefull.bus_schedule.database.AppDatabase

class BusScheduleApplication: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}