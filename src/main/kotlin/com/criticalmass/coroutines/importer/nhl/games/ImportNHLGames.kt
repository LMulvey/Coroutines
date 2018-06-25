package com.criticalmass.coroutines.importer.nhl.games

import com.criticalmass.coroutines.constants.Hashids
import com.criticalmass.coroutines.constants.NHL
import com.criticalmass.coroutines.models.Game
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ImportNHLGames {
    fun start() {
        FuelManager.instance.basePath = NHL.statsEndpoint
        val hashids = Hashids.config

        "/game/1990020001/feed/live".httpGet().responseString { request, response, result ->

            println(result)

            when (result) {
                is Result.Failure -> {
                    // handle errors
                }

                is Result.Success -> {
                    val data = result.get()

                    val factory = KotlinJsonAdapterFactory()
                    val moshi = Moshi.Builder().add(factory).build()

                    val gameAdapter = moshi.adapter(Game::class.java)
                    val game = gameAdapter.fromJson(data)?: error("Enable to parse Game data from JSON.")

                    print(game.gameData.teams)
                }
            }
        }
    }
}
