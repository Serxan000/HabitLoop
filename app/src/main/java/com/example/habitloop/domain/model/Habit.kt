package com.example.habitloop.domain.model

data class Habit(
    val id:Int,
    val title:String,
    val isDone: Boolean = false

)

