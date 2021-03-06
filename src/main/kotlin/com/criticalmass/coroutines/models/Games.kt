package com.criticalmass.coroutines.models

import com.criticalmass.coroutines.importer.nhl.plays.Plays
import com.squareup.moshi.Json
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

/**
 * The data class mapping the returned JSON fields to a variable
 */
data class Game(
  @Json(name = "gamePk") val id: Int,
  val link: String,
  val gameData: GameData,
  val liveData: LiveData
)

data class GameData(
  @Json(name = "game") val gameType: GameType,
  @Json(name = "datetime") val dateTime: GameDateTime,
  val status: GameStatus,
  val teams: GameTeams,
  val players: Map<String, Player>
)

data class GameType(
  val pk: Int,
  val season: String,
  val type: String
)

data class GameDateTime(
  val dateTime: String
)

data class GameStatus(
  val abstractGameState: String,
  val codedGameState: Int,
  val detailedState: String,
  val statusCode: Int,
  val startTimeTBD: Boolean
)

data class GameTeams(
  val away: Team,
  val home: Team
)

data class LiveData(
  val plays: Plays

)

/**
 * The Table structure
 */
object GameModel : IntIdTable("games", "increment_id") {
  /**
   * Exposed reserves the `id` variable, so we use `uid` here (Unique ID)
   * This is merely a formality and is actually mapped to the `id` column in the database
   */
  val uid: Column<Int> = integer("id").uniqueIndex()
  val link: Column<String> = varchar("link", 255)

  /**
   * gameData
   */
  // game
  val pk = integer("pk")
  val season = varchar("season", 255)
  val type = varchar("type", 255)

  // datetime
  val dateTime = varchar("date_time", 255)

  // status
  val abstractGameState = varchar("abstract_game_state", 255)
  val codedGameState = integer("coded_game_state")
  val detailedState = varchar("detailed_state", 255)
  val statusCode = integer("status_code")
  val startTimeTBD = bool("start_time_tbd")

  // teams
  val away = integer("away_team")
  val home = integer("home_team")

  // players

  /**
   * liveData
   */
  // plays
}
