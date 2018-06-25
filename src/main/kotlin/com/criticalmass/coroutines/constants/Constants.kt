package com.criticalmass.coroutines.constants

import org.hashids.Hashids

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

object Hashids {
    val salt = "criticalmass"
    val minHashLength = 16
    val alphabet = "0123456789cfhistuCFHISTU"
    val config = Hashids(salt, minHashLength, alphabet)
}

object NHL {
    val statsEndpoint = "http://statsapi.web.nhl.com/api/v1"
    val firstGameId = 1990020001
    var nextGameId = firstGameId
}
