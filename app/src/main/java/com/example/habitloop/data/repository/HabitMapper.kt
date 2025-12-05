package com.example.habitloop.data.repository

import com.example.habitloop.data.local.HabitEntity
import com.example.habitloop.domain.model.Habit

fun Habit.toHabitEntity() = HabitEntity(id, title, difficulty, progress)
fun HabitEntity.toHabit() = Habit(id, title, difficulty, progress)
