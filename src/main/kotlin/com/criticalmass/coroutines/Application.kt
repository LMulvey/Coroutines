package com.criticalmass.coroutines

import com.criticalmass.coroutines.models.Players
import com.criticalmass.coroutines.models.Teams
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import com.criticalmass.coroutines.constants.Database as DBConstants

@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    /**
     * Connect and Structure to our Primary PostgreSQL instance
     */
    prepareDatabase()

    /**
     * Trigger the Initialization of the Spring Application Framework
     */
    SpringApplication.run(Application::class.java, *args)
}

fun prepareDatabase() {
    /**
     * Pull from Constants and connect to the database using Hikari Data Pooling
     */
    val dataSource : HikariDataSource
    val url = DBConstants.url
    Class.forName("org.postgresql.Driver")

    val hikariConfig = HikariConfig()
    hikariConfig.jdbcUrl = url
    hikariConfig.username = DBConstants.username
    hikariConfig.password= DBConstants.password

    dataSource = HikariDataSource(hikariConfig)
    Database.connect(dataSource)

    /**
     * Create Database Table Structure
     * Kind of like make-shift migrations going on here
     */

    transaction {
        create(Teams, Players)
    }
}
