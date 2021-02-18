package com.thekeval.covid19tracker.network

import androidx.lifecycle.LiveData
import com.squareup.moshi.JsonClass
// import com.thekeval.covid19tracker.database.DbModel
import com.thekeval.covid19tracker.database.DbSummary
// import com.thekeval.covid19tracker.database.GlobSum

@JsonClass(generateAdapter = true)
data class NetworkModel(
    val ID: String = "",
    val Message: String = "",
    // val Global: Summary,
    val Countries: List<Summary>
)

@JsonClass(generateAdapter = true)
data class Summary(
    val ID: String = "",
    val Country: String = "",
    val CountryCode: String = "",
    val Slug: String = "",
    val NewConfirmed: Int = 0,
    val TotalConfirmed: Int = 0,
    val NewDeaths: Int = 0,
    val TotalDeaths: Int = 0,
    val NewRecovered: Int = 0,
    val TotalRecovered: Int = 0,
    val Date: String = ""
)

//fun NetworkModel.asDbModel(): DbModel {
//    return DbModel(
//        Id = Id,
//        Message = Message
//    )
//}
//
//fun NetworkModel.asGlobalSummary(): GlobSum {
//    return GlobSum(
//        DbModelId = Id,
//        NewConfirmed = Global.NewConfirmed,
//        TotalConfirmed = Global.TotalConfirmed,
//        NewDeaths = Global.NewDeaths,
//        TotalDeaths = Global.TotalDeaths,
//        NewRecovered = Global.NewRecovered,
//        TotalRecovered = Global.TotalRecovered,
//        Date = Global.Date
//    )
//}

fun List<Summary>.asDbSummary(): Array<DbSummary> {
    return map {
        DbSummary(
            Id = it.ID,
            Country = it.Country,
            CountryCode = it.CountryCode,
            Slug = it.Slug,
            NewConfirmed = it.NewConfirmed,
            TotalConfirmed = it.TotalConfirmed,
            NewDeaths = it.NewDeaths,
            TotalDeaths = it.TotalDeaths,
            NewRecovered = it.NewRecovered,
            TotalRecovered = it.TotalRecovered,
            Date = it.Date
        )
    }.toTypedArray()
}