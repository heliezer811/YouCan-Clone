package com.youcan.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WorkoutDashboard()
                }
            }
        }
    }
}

data class Exercise(val name: String, val sets: Int, val reps: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDashboard() {
    val dailyPlan = listOf(
        Exercise("Supino Reto", 4, "8-10"),
        Exercise("Agachamento Livre", 4, "10-12"),
        Exercise("Remada Curvada", 3, "10"),
        Exercise("Desenvolvimento Halteres", 3, "12")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("YouCan: Desafio IA 28 Dias") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Iniciar Treino */ }) {
                Text("Iniciar")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            ProgressHeader()
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
            ) {
                items(dailyPlan) { exercise ->
                    ExerciseCard(exercise)
                }
            }
        }
    }
}

@Composable
fun ProgressHeader() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Progresso de Hoje", style = MaterialTheme.typography.titleLarge)
        LinearProgressIndicator(
            progress = 0.25f,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp).height(8.dp)
        )
    }
}

@Composable
fun ExerciseCard(exercise: Exercise) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(exercise.name, style = MaterialTheme.typography.bodyLarge)
                Text("${exercise.sets} Séries x ${exercise.reps} Reps", style = MaterialTheme.typography.bodyMedium)
            }
            Button(onClick = { /* Registrar log */ }) {
                Text("Log")
            }
        }
    }
}
