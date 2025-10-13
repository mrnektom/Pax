package org.pax.messenger.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.bind
import javax.sql.DataSource
import kotlin.system.exitProcess

fun Module.configureDatabase() {
    single {

        val configuration = get<ApplicationEnvironment>().config.let { config ->
            HikariConfig().apply {
                jdbcUrl = config.property("database.url").getString()
                driverClassName = "org.postgresql.Driver"
                maximumPoolSize = 10
                isAutoCommit = true

                username = config.property("database.username").getString()
                password = config.property("database.password").getString()

                validate()
            }
        }

        val dataSource = HikariDataSource(configuration)
        val result = Flyway.configure().loggers("slf4j").dataSource(dataSource).load().migrate()
        if (!result.success) {
            result.exceptionObject?.printStackTrace()
            exitProcess(1)
        }

        dataSource
    } bind DataSource::class

    single(createdAtStart = true) {
        get<DataSource>().let { dataSource ->
            val config = DatabaseConfig {
                keepLoadedReferencesOutOfTransaction = true
            }

            Database.connect(dataSource, databaseConfig = config)
        }
    }
}