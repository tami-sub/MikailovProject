package com.example.mikailovproject.di

import android.app.Application
import androidx.room.Room
import com.example.mikailovproject.shared.finalproject.core.data.LoanDatabase
import com.example.mikailovproject.shared.finalproject.core.data.local.LoanDao
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {
    @Provides
    fun provideLoanDatabase(application: Application): LoanDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            LoanDatabase::class.java,
            "loan_database.db"
        ).build()
    }

    @Provides
    fun provideLoanDao(loanDatabase: LoanDatabase): LoanDao {
        return loanDatabase.loanDao()
    }
}