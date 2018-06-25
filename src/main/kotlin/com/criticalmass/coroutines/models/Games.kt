package com.criticalmass.coroutines.models

import com.squareup.moshi.Json
import java.util.*

data class Game(
    @Json(name = "gamePk") val id: String,
    val link: String,
    val gameData: GameData
)

data class GameData(
    @Json(name = "game") val gameType: GameType,
    val dateTime: GameDateTime,
    val status: GameStatus,
    val teams: GameTeams
//    val players: Map<String, Player>
)

data class GameType(
    @Json(name = "pk") val id: String,
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
