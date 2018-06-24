package com.criticalmass.coroutines

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import com.criticalmass.coroutines.constants.Database as DBConstants

@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    val dataSource : HikariDataSource

    val url = DBConstants.url
    Class.forName("org.postgresql.Driver")

    val hikariConfig = HikariConfig()
    hikariConfig.jdbcUrl = url
    hikariConfig.username = DBConstants.username
    hikariConfig.password= DBConstants.password

    dataSource = HikariDataSource(hikariConfig)
    Database.connect(dataSource)

    SpringApplication.run(Application::class.java, *args)
}
