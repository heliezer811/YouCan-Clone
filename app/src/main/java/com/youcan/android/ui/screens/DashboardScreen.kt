// ui/screens/DashboardScreen.kt
package com.youcan.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youcan.android.ui.viewmodel.WorkoutViewModel

@Composable
fun DashboardScreen(onStartWorkout: () -> Unit, viewModel: WorkoutViewModel) {
    val hydration by viewModel.hydrationLevel.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Plano de Hoje: Força Total", style = MaterialTheme.typography.headlineMedium)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Hidratação (Copos: $hydration/8)")
                LinearProgressIndicator(
                    progress = hydration / 8f,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )
                Button(onClick = { viewModel.addWater() }, modifier = Modifier.padding(top = 8.dp)) {
                    Text("+ Água")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onStartWorkout,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("Iniciar Treino do Dia")
        }
    }
}

// ui/screens/ActiveWorkoutScreen.kt
package com.youcan.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youcan.android.ui.viewmodel.WorkoutViewModel

@Composable
fun ActiveWorkoutScreen(onFinish: () -> Unit, viewModel: WorkoutViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Agachamento Livre", style = MaterialTheme.typography.headlineLarge)
        Text("Série 1 de 4 • 12 Repetições", style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = { /* Lógica de timer de descanso */ }) {
                Text("Descanso 60s")
            }
            Button(onClick = { 
                viewModel.finishWorkout("Agachamento", 4)
                onFinish() 
            }) {
                Text("Finalizar Exercício")
            }
        }
    }
}
