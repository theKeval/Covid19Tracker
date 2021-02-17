package com.thekeval.covid19tracker.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

interface CovidDao {
    
    @Query("select * from DbSummary")
    fun getSummary(): LiveData<List<DbSummary>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg summary: DbSummary)
}

@Database(entities = [DbModel::class, DbSummary::class, GlobSum::class], version = 1)
abstract class CovidDatabase: RoomDatabase() {
    abstract val covidDao: CovidDao
}

private lateinit var INSTANCE: CovidDatabase

fun getDatabase(context: Context): CovidDatabase {
    
    synchronized(CovidDatabase::class.java) {
        
        if (!::INSTANCE.isInitialized) {
            
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CovidDatabase::class.java,
                "CovidSummary").build()
        }
    }
    
    return INSTANCE
}