package com.example.habitloop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.habitloop.presentation.HabitViewModel
import com.example.habitloop.presentation.screens.HabitListScreen
import com.example.habitloop.ui.theme.HabitLoopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitLoopTheme {
                HabitListScreen(
                    viewModel = ViewModelProvider(this)[HabitViewModel::class.java],
                    onOpenHabit = { id ->
                        // Hələlik sadəcə println
                        println("Habit clicked: $id")
                    }
                )

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabitLoopTheme {

    }
}