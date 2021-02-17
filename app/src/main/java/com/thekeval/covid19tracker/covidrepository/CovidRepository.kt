package com.thekeval.covid19tracker.covidrepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.thekeval.covid19tracker.database.CovidDatabase
import com.thekeval.covid19tracker.database.asDomainModel
import com.thekeval.covid19tracker.domain.DomainModel
import com.thekeval.covid19tracker.network.Network
import com.thekeval.covid19tracker.network.Summary
import com.thekeval.covid19tracker.network.asDbSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CovidRepository(private val database: CovidDatabase) {

    val summary: LiveData<List<DomainModel>> = Transformations.map(database.covidDao.getSummary()) {
        it.asDomainModel()
    }

    suspend fun refreshSummary() {
        withContext(Dispatchers.IO) {
            val response = Network.covid19.getData().await()
            database.covidDao.insertAll(*response.Countries.asDbSummary())
        }
    }

}