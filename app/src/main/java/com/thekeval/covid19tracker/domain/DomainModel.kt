package com.thekeval.covid19tracker.domain

data class DomainModel(
    val Country: String = "",
    val NewConfirmed: String = "",
    val TotalConfirmed: String = "",
    val NewDeaths: String = "",
    val TotalDeaths: String = "",
    val NewRecovered: String = "",
    val TotalRecovered: String = "",
    val Date: String = ""
)