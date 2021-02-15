package com.thekeval.covid19tracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
data class DbModel(
    @PrimaryKey
    val Id: String,
    val Message: String,
    val Global: DbSummary,
    val Countries: List<DbSummary>
)

@Entity
data class GlobSum(
    @PrimaryKey
    val Id: String,
    val NewConfirmed: Int,
    val TotalConfirmed: Int,
    val NewDeaths: Int,
    val TotalDeaths: Int,
    val NewRecovered: Int,
    val TotalRecovered: Int,
    val Date: String
)

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