package com.criticalmass.coroutines.importer.nhl.players

import com.criticalmass.coroutines.helpers.postgresql.insertOrUpdate
import com.criticalmass.coroutines.models.Player
import com.criticalmass.coroutines.models.PlayerModel
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

class ImportNHLPlayers(val players: Map<String, Player>) {
  fun start() {
    for (player in players.values) {
      transaction {
        logger.addLogger(StdOutSqlLogger)

        PlayerModel.insertOrUpdate(PlayerModel.uid) {
          it[uid] = player.id
          it[fullName] = player.fullName
          it[link] = player.link
          it[firstName] = player.firstName
          it[lastName] = player.lastName
          it[primaryNumber] = player.primaryNumber
          it[birthDate] = player.birthDate
          it[birthCity] = player.birthCity
          it[birthStateProvince] = player.birthStateProvince
          it[birthCountry] = player.birthCountry
          it[nationality] = player.nationality
          it[height] = player.height
          it[weight] = player.weight
          it[active] = player.active
          it[rookie] = player.rookie
          it[shootsCatches] = player.shootsCatches
          it[rosterStatus] = player.rosterStatus
        }
      }
    }
  }
}
