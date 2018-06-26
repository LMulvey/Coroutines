package com.criticalmass.coroutines.importer.nhl.teams

import com.criticalmass.coroutines.helpers.postgresql.insertOrUpdate
import com.criticalmass.coroutines.models.Team
import com.criticalmass.coroutines.models.TeamModel
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

class ImportNHLTeams(val team: Team) {
  fun start() {
    transaction {
      logger.addLogger(StdOutSqlLogger)

      TeamModel.insertOrUpdate(TeamModel.uid) {
        it[uid] = team.id
        it[name] = team.name
        it[link] = team.link
        it[abbreviation] = team.abbreviation
        it[triCode] = team.triCode
        it[teamName] = team.teamName
        it[locationName] = team.locationName
        it[firstYearOfPlay] = team.firstYearOfPlay
        it[shortName] = team.shortName
        it[officialSiteUrl] = team.officialSiteUrl
        it[franchiseId] = team.franchiseId
        it[active] = team.active
      }
    }
  }
}
