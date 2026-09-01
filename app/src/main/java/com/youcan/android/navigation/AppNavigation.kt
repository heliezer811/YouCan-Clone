// navigation/AppNavigation.kt
package com.youcan.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.youcan.android.ui.screens.DashboardScreen
import com.youcan.android.ui.screens.ActiveWorkoutScreen
import com.youcan.android.ui.viewmodel.WorkoutViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: WorkoutViewModel = viewModel()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                onStartWorkout = { navController.navigate("active_workout") },
                viewModel = viewModel
            )
        }
        composable("active_workout") {
            ActiveWorkoutScreen(
                onFinish = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}
