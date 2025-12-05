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

    /** Yeni habit əlavə etmək */
    fun addHabit(title: String, difficulty: String) = viewModelScope.launch {
        val habit = Habit(
            id = 0,
            title = title,
            difficulty = difficulty,
            progress = 0
        )
        repo.insertHabit(habit)
    }

    /** Progress +1 (max 21) */
    fun increaseProgress(habit: Habit) = viewModelScope.launch {
        if (habit.progress < 21) {
            val updated = habit.copy(progress = habit.progress + 1)
            repo.updateHabit(updated)
        }
    }

    /** Progress -1 (optional) */
    fun decreaseProgress(habit: Habit) = viewModelScope.launch {
        if (habit.progress > 0) {
            val updated = habit.copy(progress = habit.progress - 1)
            repo.updateHabit(updated)
        }
    }

    /** Habit silmə funksiyası */
    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        repo.deleteHabit(habit)
    }
}
