package com.example.mikailovproject.shared.finalproject.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mikailovproject.shared.finalproject.core.data.local.LoanDao
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity

@Database(entities = [LoanEntity::class], version = 1)
abstract class LoanDatabase : RoomDatabase() {
    abstract fun loanDao(): LoanDao

    companion object {
        @Volatile
        private var INSTANCE: LoanDatabase? = null

        fun getInstance(context: Context): LoanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoanDatabase::class.java,
                    "loan_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}