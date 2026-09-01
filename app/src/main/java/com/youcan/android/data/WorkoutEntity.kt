// data/WorkoutEntity.kt
package com.youcan.android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_history")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exerciseName: String,
    val completedSets: Int,
    val dateTimestamp: Long
)

// data/WorkoutDao.kt
package com.youcan.android.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM workout_history ORDER BY dateTimestamp DESC")
    fun getAllHistory(): Flow<List<WorkoutEntity>>
}
