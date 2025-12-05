package com.example.habitloop.domain.repository

import com.example.habitloop.data.local.HabitDao
import com.example.habitloop.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun insertHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
    fun getHabits(): Flow<List<Habit>>
}

