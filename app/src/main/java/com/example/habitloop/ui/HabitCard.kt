package com.example.habitloop.ui

import androidx.compose.ui.Alignment



import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.habitloop.domain.model.Habit

@Composable
fun HabitCard(habit: Habit, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            CircularProgressIndicator(
                progress = habit.progress / 21f,
                strokeWidth = 6.dp,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(habit.title, style = MaterialTheme.typography.titleMedium)
                Text("Difficulty: ${habit.difficulty}")
                Text("Progress: ${habit.progress}/21 days")
            }
        }
    }
}

