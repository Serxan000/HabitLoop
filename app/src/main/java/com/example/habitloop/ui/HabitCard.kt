package com.example.habitloop.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.habitloop.domain.model.Habit

@Composable
fun HabitCard(
    habit: Habit,
    onClick: () -> Unit,
    onProgressUpdate: (Int) -> Unit,
    onTaskCompleted: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }

    val progressFraction by animateFloatAsState(targetValue = habit.progress / 21f)

    // Son checkbox dolduqda popup göstər
    LaunchedEffect(habit.progress) {
        if (habit.progress >= 21) {
            showPopup = true
            kotlinx.coroutines.delay(1500)
            onTaskCompleted()
            showPopup = false
        }
    }

    Card(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularProgressIndicator(
                    progress = progressFraction,
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

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Column {
                    for (day in 1..21) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            val isDone = day <= habit.progress
                            Checkbox(
                                checked = isDone,
                                onCheckedChange = { onProgressUpdate(day) }
                            )
                            Text("Day $day")
                        }
                    }
                }
            }
        }
    }

    // Popup göstər
    if (showPopup) {
        Popup(alignment = Alignment.Center) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Done",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Task successfully completed!",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}



