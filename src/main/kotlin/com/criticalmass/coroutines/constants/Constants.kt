package com.criticalmass.coroutines.constants

object Database {
  val protocol = "jdbc"
  val type = "postgresql"
  val host = "127.0.0.1"
  val port = "54320"
  val name = "cmcoroutines"
  val username = "homestead"
  val password = "secret"
  val url = "${protocol}:${type}://${host}:${port}/${name}"
}

object NHL {
  val statsEndpoint = "http://statsapi.web.nhl.com/api/v1"
}
