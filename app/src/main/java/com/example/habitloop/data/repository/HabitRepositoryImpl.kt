package com.example.habitloop.data.repository

import com.example.habitloop.data.local.HabitDao
import com.example.habitloop.domain.model.Habit
import com.example.habitloop.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
) : HabitRepository
{
    override suspend fun insertHabit(habit: Habit) {
        habitDao.insert(habit.toHabitEntity())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.delete(habit.toHabitEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.update(habit.toHabitEntity())
    }

    override fun getHabits(): Flow<List<Habit>> =
        habitDao.getAllHabits().map { list -> list.map { it.toHabit() } }


}