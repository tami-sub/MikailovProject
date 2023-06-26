package com.example.mikailovproject.shared.finalproject.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity

@Dao
interface LoanDao {
    @Query("SELECT * FROM loans")
    suspend fun getAllLoans(): List<LoanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoans(loans: List<LoanEntity>)

    @Query("DELETE FROM loans")
    suspend fun deleteAllLoans()
}