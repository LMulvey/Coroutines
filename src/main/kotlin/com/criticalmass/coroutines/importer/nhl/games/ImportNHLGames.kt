package com.criticalmass.coroutines.importer.nhl.games

import com.criticalmass.coroutines.constants.NHL
import com.criticalmass.coroutines.constants.NHLImportStats
import com.criticalmass.coroutines.helpers.postgresql.insertOrUpdate
import com.criticalmass.coroutines.importer.nhl.players.ImportNHLPlayers
import com.criticalmass.coroutines.importer.nhl.plays.ImportNHLPlays
import com.criticalmass.coroutines.importer.nhl.teams.ImportNHLTeams
import com.criticalmass.coroutines.models.Game
import com.criticalmass.coroutines.models.GameModel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.experimental.launch
import org.jetbrains.exposed.sql.transactions.transaction

class ImportNHLGames() {
  fun start() {
    /**
     * Define basePath and then Import game with ID
     */
    FuelManager.instance.basePath = NHL.statsEndpoint

    for (gameId in 1..1280) {
      println("/game/201702${String.format("%04d", gameId)}/feed/live")
      "/game/201702${String.format("%04d", gameId)}/feed/live".httpGet().responseString { _, response, result ->
        when (result) {
          is Result.Failure -> {
            // handle errors
          }

          is Result.Success -> {
            val data = result.get()

            val factory = KotlinJsonAdapterFactory()
            val moshi = Moshi.Builder().add(factory).build()

            val gameAdapter = moshi.adapter(Game::class.java)
            val game = gameAdapter.fromJson(data) ?: error("Enable to parse Game data from JSON.")

            /**
             * Dispatch Import Events
             */
            // Plays
            launch {
              // ImportNHLPlays(game.liveData.plays).start()
            }

            // Players
            launch {
              ImportNHLPlayers(game.gameData.players).start()
            }

            // Teams
            launch {
              ImportNHLTeams(game.gameData.teams.away).start()
              ImportNHLTeams(game.gameData.teams.home).start()
            }

            /**
             * Store the Game Object to the Database
             */
            transaction {
              GameModel.insertOrUpdate(GameModel.uid) {
                it[uid] = game.id
                it[link] = game.link

                it[pk] = game.gameData.gameType.pk
                it[season] = game.gameData.gameType.season
                it[type] = game.gameData.gameType.type

                it[dateTime] = game.gameData.dateTime.dateTime

                it[abstractGameState] = game.gameData.status.abstractGameState
                it[codedGameState] = game.gameData.status.codedGameState
                it[detailedState] = game.gameData.status.detailedState
                it[statusCode] = game.gameData.status.statusCode
                it[startTimeTBD] = game.gameData.status.startTimeTBD

                it[away] = game.gameData.teams.away.id
                it[home] = game.gameData.teams.home.id
              }
            }
          }
        }
      }
    }
  }
}
