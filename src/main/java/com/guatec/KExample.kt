package com.guatec

import org.apache.log4j.Logger
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.MigrationVersion

/**
 * Created by juanliska on 1/11/16.
 */

object Bob {
    private val logger = Logger.getLogger(Example::class.java)
}

class KExample{
    val ok = true

    companion object {
        @JvmStatic
        fun main(args : Array<String>) {
            val (url, user, password) = args.slice(0..2)

            //must comment-out the next line to build with Kobalt 0.810
            val example = Example()

            val flyway = Flyway()
            flyway.setDataSource(url, user, password)

            flyway.target = MigrationVersion.fromVersion("1.12")
            flyway.migrate()
        }
    }
}