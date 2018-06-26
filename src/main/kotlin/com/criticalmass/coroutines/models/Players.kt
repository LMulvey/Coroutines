package com.criticalmass.coroutines.models

import org.jetbrains.exposed.dao.IntIdTable

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
  val primaryNumber: Int?,
  val birthDate: String,
  val birthCity: String?,
  val birthStateProvince: String?,
  val birthCountry: String?,
  val nationality: String?,
  val height: String?,
  val weight: Int?,
  val active: Boolean,
  val rookie: Boolean,
  val shootsCatches: String?,
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
  val uid = integer("id").uniqueIndex()
  val fullName = varchar("full_name", 255)
  val link = varchar("link", 255)
  val firstName = varchar("first_name", 255)
  val lastName = varchar("last_name", 255)
  val primaryNumber = integer("primary_number").nullable()
  val birthDate = varchar("birth_date", 255)
  val birthCity = varchar("birth_city", 255).nullable()
  val birthStateProvince = varchar("birth_state_province", 255).nullable()
  val birthCountry = varchar("birth_country", 255).nullable()
  val nationality = varchar("nationality", 255).nullable()
  val height = varchar("height", 255).nullable()
  val weight = integer("weight").nullable()
  val active = bool("active")
  val rookie = bool("rookie")
  val shootsCatches = varchar("shoots_catches", 255).nullable()
  val rosterStatus = varchar("roster_status", 255)
}
