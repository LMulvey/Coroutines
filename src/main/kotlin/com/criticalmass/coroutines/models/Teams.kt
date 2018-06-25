package com.criticalmass.coroutines.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

data class Team(
    val id: Int,
    val name: String,
    val link: String,
//    val value: String,
    val abbreviation: String,
    val triCode: String,
    val teamName: String,
    val locationName: String,
    val firstYearOfPlay: String,
//    val division: String,
//    val franchise: String,
//    val conference: String,
    val shortName: String,
    val officialSiteUrl: String,
    val franchiseId: String,
    val active: Boolean
)

object TeamModel : IntIdTable("teams", "increment_id") {
    /**
     * Exposed reserves the `id` variable, so we use `uid` here (Unique ID)
     * This is merely a formality and is actually mapped to the `id` column in the database
     */
    val uid: Column<Int> = integer("id").uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val link: Column<String> = varchar("link", 255)
    val abbreviation: Column<String> = varchar("abbreviation", 255)
    val triCode: Column<String> = varchar("tricode", 255)
    val teamName: Column<String> = varchar("team_name", 255)
    val locationName: Column<String> = varchar("location_name", 255)
    val firstYearOfPlay: Column<String> = varchar("first_year_of_play", 255)
    val shortName: Column<String> = varchar("short_name", 255)
    val officialSiteUrl: Column<String> = varchar("official_site_url", 255)
    val franchiseId: Column<String> = varchar("franchise_id", 255)
    val active: Column<Boolean> = bool("active")
}
