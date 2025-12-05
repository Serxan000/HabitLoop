package com.example.habitloop.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.habitloop.domain.model.Habit
import com.example.habitloop.presentation.HabitViewModel
import com.example.habitloop.ui.HabitCard

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitListScreen(
    viewModel: HabitViewModel = hiltViewModel(),
    onOpenHabit: (Int) -> Unit
) {
    val habits by viewModel.habits.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("HabitLoop") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(habits) { habit ->
                HabitCard(habit = habit, onClick = { onOpenHabit(habit.id) })
            }
        }

        if (showDialog) {
            AddHabitDialog(
                onAdd = { title, dif ->
                    viewModel.addHabit(title,dif)
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

