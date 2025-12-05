package com.example.habitloop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habitloop.domain.model.DailyCheckEntity

@Database(entities = [HabitEntity::class, DailyCheckEntity::class], version = 2)
abstract class HabitDatabase: RoomDatabase() {
    abstract fun getDao(): HabitDao
}

