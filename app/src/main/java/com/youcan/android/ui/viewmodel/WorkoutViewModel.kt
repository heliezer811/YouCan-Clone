// ui/viewmodel/WorkoutViewModel.kt
package com.youcan.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youcan.android.data.WorkoutEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel : ViewModel() {
    private val _hydrationLevel = MutableStateFlow(0)
    val hydrationLevel: StateFlow<Int> = _hydrationLevel.asStateFlow()

    fun addWater() {
        if (_hydrationLevel.value < 8) {
            _hydrationLevel.value += 1
        }
    }

    fun finishWorkout(name: String, sets: Int) {
        viewModelScope.launch {
            val workout = WorkoutEntity(
                exerciseName = name,
                completedSets = sets,
                dateTimestamp = System.currentTimeMillis()
            )
            // Aqui você chamaria: workoutDao.insertWorkout(workout)
        }
    }
}
