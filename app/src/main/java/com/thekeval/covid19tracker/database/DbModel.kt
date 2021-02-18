package com.thekeval.covid19tracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import com.thekeval.covid19tracker.domain.DomainModel

//@Entity
//data class DbModel(
//    @PrimaryKey
//    val Id: String,
//    val Message: String,
//    // val Global: GlobSum,
//    // val Countries: List<DbSummary>
//)
//
//@Entity
//data class GlobSum(
//    @PrimaryKey
//    val DbModelId: String,
//    val NewConfirmed: Int,
//    val TotalConfirmed: Int,
//    val NewDeaths: Int,
//    val TotalDeaths: Int,
//    val NewRecovered: Int,
//    val TotalRecovered: Int,
//    val Date: String
//)

@Entity
data class DbSummary(
    @PrimaryKey
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

fun List<DbSummary>.asDomainModel(): List<DomainModel> {
    return map {
        DomainModel(
            Country = it.Country,
            NewConfirmed = it.NewConfirmed.toString(),
            TotalConfirmed = it.TotalConfirmed.toString(),
            NewDeaths = it.NewDeaths.toString(),
            TotalDeaths = it.TotalDeaths.toString(),
            NewRecovered = it.NewRecovered.toString(),
            TotalRecovered = it.TotalRecovered.toString(),
            Date = it.Date
        )
    }
}