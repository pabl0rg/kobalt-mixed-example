package com.guatec

import com.guatec.internal.Version
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.MigrationVersion

object FlywayMigrator {
    @JvmStatic fun main(args: Array<String>) {
        val version = Version.getShortVersion()
        println("\n=== Applying DB Migrations up to $version  ===")

        with(Flyway()) {
            setDataSource("jdbc:h2:./db.h2", "sa", "")
            target = MigrationVersion.fromVersion(version)
            migrate()
            dataSource.connection.use { con ->
                con.createStatement().use { stmt ->
                    stmt.executeQuery("SELECT * FROM bob").use { rs ->
                        println(rs.fetchSize)
                    }
                }
            }
        }
    }
}