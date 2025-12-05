package com.example.habitloop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitloop.domain.model.Habit
import com.example.habitloop.domain.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val repo: HabitRepository
) : ViewModel() {

    val habits: StateFlow<List<Habit>> = repo.getHabits()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addHabit(title: String, difficulty: String) = viewModelScope.launch {
        val habit = Habit(
            id = 0,
            title = title,
            difficulty = difficulty,
            progress = 0
        )
        repo.insertHabit(habit)
    }

    fun updateHabitProgress(habit: Habit, day: Int) = viewModelScope.launch {
        val newProgress = if (day > habit.progress) day else habit.progress
        val updatedHabit = habit.copy(progress = newProgress)
        repo.updateHabit(updatedHabit)
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        repo.deleteHabit(habit)
    }
}
