package com.criticalmass.coroutines.importer.nhl.plays

import com.criticalmass.coroutines.models.PlayModel
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class ImportNHLPlays(val plays: Plays) {
  fun start() {
    for (play in plays.allPlays) {
      transaction {
        logger.addLogger(StdOutSqlLogger)

        PlayModel.insert {
          it[event] = play.result.event
          it[description] = play.result.description
        }
      }
    }
  }
}

data class Plays(
  val allPlays: Array<AllPlays>
)

data class AllPlays(
  val players: Any?,
  val result: AllPlaysResult,
  val about: Any?,
  val coordinates: Any?,
  val team: Any?
)

data class AllPlaysResult(
  val event: String,
  val description: String
)
