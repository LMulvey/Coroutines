package com.criticalmass.coroutines.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

object Players : IntIdTable("players", "increment_id") {
    /**
     * Exposed reserves the `id` variable, so we use `uid` here (Unique ID)
     * This is merely a formality and is actually mapped to the `id` column in the database
     */
    val uid: Column<Long> = Players.long("id").uniqueIndex()
    val firstName: Column<String> = Players.varchar("first_name", 255)
    val lastName: Column<String> = Players.varchar("last_name", 255)
}
