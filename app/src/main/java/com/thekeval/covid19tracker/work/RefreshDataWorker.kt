package com.thekeval.covid19tracker.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.thekeval.covid19tracker.covidrepository.CovidRepository
import com.thekeval.covid19tracker.database.getDatabase
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = CovidRepository(database)

        return try {
            repository.refreshSummary()
            Result.success()
        }
        catch (exception: HttpException) {
            Result.retry()
        }

    }
}