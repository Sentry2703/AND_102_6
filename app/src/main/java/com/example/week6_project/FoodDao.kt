package com.example.week6_project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM Food_Table")
    fun getAll(): Flow<List<FoodEntity>>

    @Insert
    fun insertAll(foods: List<FoodEntity>)

    @Insert()
    fun insert(food: FoodEntity)

    @Query("DELETE FROM Food_Table")
    fun deleteAll()
}