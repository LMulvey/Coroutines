package com.criticalmass.coroutines.constants

object Database {
    val protocol = "jdbc"
    val type = "postgresql"
    val host = "//127.0.0.1"
    val port = "54320"
    val name = "coroutines"
    val username = "homestead"
    val password = "secret"
    val url = "${protocol}:${type}:${host}:${port}/${name}"
}

object Hashids {
    val salt = "criticalmass"
    val minHashLength = 16
    val alphabet = "0123456789cfhistuCFHISTU"
}
