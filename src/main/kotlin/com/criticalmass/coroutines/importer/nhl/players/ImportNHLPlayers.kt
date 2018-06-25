package com.criticalmass.coroutines.importer.nhl.players

import com.criticalmass.coroutines.constants.Hashids
import com.criticalmass.coroutines.constants.NHL
import com.criticalmass.coroutines.models.Game
import com.criticalmass.coroutines.models.Player
import com.criticalmass.coroutines.models.Players
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class ImportNHLPlayers {
    fun start() {
        FuelManager.instance.basePath = NHL.statsEndpoint
        val hashids = Hashids.config

        "/people/8447775".httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    // Error Handling
                }

                is Result.Success -> {
                    val data = result.get()

                    val factory = KotlinJsonAdapterFactory()
                    val moshi = Moshi.Builder().add(factory).build()

                    val playerAdapter = moshi.adapter(Player::class.java)
                    val player = playerAdapter.fromJson(data)?: error("Unable to parse Game from JSON.")

                    for (person in player.people) transaction {
                        val hash = hashids.encode(person.id.toLong()).toLong()

                        Players.insert {
                            it[uid] = hash
                            it[firstName] = person.firstName
                            it[lastName] = person.lastName
                        }
                    }
                }
            }
        }
    }
}
