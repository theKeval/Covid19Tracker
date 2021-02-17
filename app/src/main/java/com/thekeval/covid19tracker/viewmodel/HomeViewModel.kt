package com.thekeval.covid19tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.thekeval.covid19tracker.covidrepository.CovidRepository
import com.thekeval.covid19tracker.database.CovidDatabase
import com.thekeval.covid19tracker.database.getDatabase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = CovidRepository(database)

    init {
        viewModelScope.launch {
            repository.refreshSummary()
        }
    }

    val summaries = repository.summaries
    

    class Factory(private val app: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }

            throw IllegalArgumentException("Unable to construct ViewModel")
        }

    }

}