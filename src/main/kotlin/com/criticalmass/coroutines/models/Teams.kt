package com.criticalmass.coroutines.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

object Teams : IntIdTable("teams", "increment_id") {
    /**
     * Exposed reserves the `id` variable, so we use `uid` here (Unique ID)
     * This is merely a formality and is actually mapped to the `id` column in the database
     */
    val uid: Column<Long> = Teams.long("id").uniqueIndex()
    val city: Column<String> = Teams.varchar("city", 255)
    val name: Column<String> = Teams.varchar("name", 255)
}
