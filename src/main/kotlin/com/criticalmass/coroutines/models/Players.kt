package com.criticalmass.coroutines.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

/**
 * The data class mapping the returned JSON fields to a variable
 */
data class Player(
    val people: List<Person>
)

data class Person(
    val id: Int,
    val fullName: String,
    val link: String,
    val firstName: String,
    val lastName: String,
    val primaryNumber: String,
    val birthDate: String,
    val birthCity: String,
    val birthStateProvince: String,
    val birthCountry: String,
    val nationality: String,
    val height: String,
    val weight: String,
    val active: Boolean,
    val rookie: Boolean,
    val shootsCatches: String,
    val rosterStatus: String
)

/**
 * The Table structure
 */
object Players : IntIdTable("players", "increment_id") {
    /**
     * Exposed reserves the `id` variable, so we use `uid` here (Unique ID)
     * This is merely a formality and is actually mapped to the `id` column in the database
     */
    val uid: Column<Long> = Players.long("id").uniqueIndex()
    val firstName: Column<String> = Players.varchar("first_name", 255)
    val lastName: Column<String> = Players.varchar("last_name", 255)
}
