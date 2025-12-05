package com.example.habitloop.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_checks")
data class DailyCheckEntity(
    val habitId: Int,
    val day: Int, // 1..21
    val isDone: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
