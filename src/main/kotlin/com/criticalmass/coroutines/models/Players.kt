package com.criticalmass.coroutines.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

/**
 * The data class mapping the returned JSON fields to a variable
 */
data class PlayersList(
    val people: List<Player>
)

data class Player(
    val id: Int,
    val fullName: String,
    val link: String,
    val firstName: String,
    val lastName: String,
    val primaryNumber: Int,
    val birthDate: String,
    val birthCity: String,
    val birthStateProvince: String?,
    val birthCountry: String,
    val nationality: String,
    val height: String,
    val weight: Int,
    val active: Boolean,
    val rookie: Boolean,
    val shootsCatches: String,
    val rosterStatus: String
)

/**
 * The Table structure
 */
object PlayerModel : IntIdTable("players", "increment_id") {
    /**
     * Exposed reserves the `id` variable, so we use `uid` here (Unique ID)
     * This is merely a formality and is actually mapped to the `id` column in the database
     */
    val uid = integer("uid")
    val fullName = varchar("fullName", 255)
    val link = varchar("link", 255)
    val firstName = varchar("firstName", 255)
    val lastName = varchar("lastName", 255)
    val primaryNumber = integer("primaryNumber")
    val birthDate = varchar("birthDate", 255)
    val birthCity = varchar("birthCity", 255)
    val birthStateProvince = varchar("birthStateProvince", 255).nullable()
    val birthCountry = varchar("birthCountry", 255)
    val nationality = varchar("nationality", 255)
    val height = varchar("height", 255)
    val weight = integer("weight")
    val active = bool("active")
    val rookie = bool("rookie")
    val shootsCatches = varchar("shootsCatches", 255)
    val rosterStatus = varchar("rosterStatus", 255)
}
