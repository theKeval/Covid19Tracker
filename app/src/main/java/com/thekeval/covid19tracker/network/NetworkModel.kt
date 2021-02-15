package com.thekeval.covid19tracker.network

import androidx.lifecycle.LiveData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkModel(
    val Id: String,
    val Message: String,
    val Global: Summary,
    val Countries: List<Summary>
)

@JsonClass(generateAdapter = true)
data class Summary(
    val Id: String,
    val Country: String,
    val CountryCode: String,
    val Slug: String,
    val NewConfirmed: Int,
    val TotalConfirmed: Int,
    val NewDeaths: Int,
    val TotalDeaths: Int,
    val NewRecovered: Int,
    val TotalRecovered: Int,
    val Date: String

)