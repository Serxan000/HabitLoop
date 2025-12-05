package com.example.habitloop.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface HabitDao {


    @Insert
    suspend fun insert(habit: HabitEntity)

    @Update
    suspend fun update(habit: HabitEntity)

    @Query("SELECT * FROM habits")
     fun getAllHabits(): Flow<List<HabitEntity>>

    @Delete
    suspend fun delete(habit: HabitEntity)


}