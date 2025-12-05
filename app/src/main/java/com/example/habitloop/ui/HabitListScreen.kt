package com.example.habitloop.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.habitloop.presentation.HabitViewModel
import com.example.habitloop.ui.HabitCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitListScreen(
    viewModel: HabitViewModel = hiltViewModel(),
    onOpenHabit: (Int) -> Unit = {}
) {
    val habits by viewModel.habits.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(title = { Text("HabitLoop") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->

        if (habits.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hələ heç bir vərdişiniz yoxdur. Başlamaq üçün '+' düyməsinə basın!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(24.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(habits, key = { it.id }) { habit ->
                HabitCard(
                    habit = habit,
                    onClick = { onOpenHabit(habit.id) },
                    onProgressUpdate = { day ->
                        coroutineScope.launch {
                            viewModel.updateHabitProgress(habit, day)
                        }
                    },
                    onTaskCompleted = {
                        coroutineScope.launch {
                            viewModel.deleteHabit(habit)
                        }
                    }
                )


            }
        }

        if (showDialog) {
            AddHabitDialog(
                onAdd = { title, difficulty ->
                    viewModel.addHabit(title, difficulty)
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}
