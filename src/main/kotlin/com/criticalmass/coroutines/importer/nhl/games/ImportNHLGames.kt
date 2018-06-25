package com.criticalmass.coroutines.importer.nhl.games

import com.criticalmass.coroutines.constants.NHL
import com.criticalmass.coroutines.helpers.postgresql.insertOrUpdate
import com.criticalmass.coroutines.importer.nhl.GameTargetHandler
import com.criticalmass.coroutines.importer.nhl.players.ImportNHLPlayers
import com.criticalmass.coroutines.importer.nhl.teams.ImportNHLTeams
import com.criticalmass.coroutines.models.Game
import com.criticalmass.coroutines.models.GameModel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.experimental.async
import org.jetbrains.exposed.sql.transactions.transaction

class ImportNHLGames(val gameId: Int) {
    fun start() {
        println("Importing Game: $gameId")

        /**
         * Define basePath and then Import game with ID
         */
        FuelManager.instance.basePath = NHL.statsEndpoint
        async {
            "/game/$gameId/feed/live".httpGet().responseString { _, _, result ->
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
                        // Teams
                        async {
                            ImportNHLTeams(game.gameData.teams.away).start()
                            ImportNHLTeams(game.gameData.teams.home).start()
                        }

                        // Players
                        async {
                            ImportNHLPlayers(game.gameData.players).start()
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

        /**
         * Trigger the next loop to happen
         */
        ImportNHLGames(
            GameTargetHandler().getNextGame()
        ).start()
    }
}
