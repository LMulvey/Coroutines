package com.criticalmass.coroutines.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

/**
 * The Table structure
 */
object PlayModel : IntIdTable("plays", "increment_id") {
  /**
   * Results
   */
  val event = varchar("event", 255)
  val description = varchar("description", 255)
}
