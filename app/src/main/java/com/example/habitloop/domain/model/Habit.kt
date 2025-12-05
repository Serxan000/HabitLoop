package com.example.habitloop.domain.model

data class Habit(
    val id: Int = 0,
    val title: String,
    val difficulty: String,
    val progress: Int = 0 // 0..21
)

