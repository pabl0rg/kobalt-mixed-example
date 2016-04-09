import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.kotlin.*

val repos = repos()

val p = project {
    name = "mixed-example"
    group = "com.guatec"
    artifactId = name
    version = "0.1"
    
    dependencies {
        compile("log4j:log4j:1.2.17")
        compile("com.squareup.okio:okio:1.6.0")
        compile("com.squareup.okhttp:okhttp:2.7.4")
        compile("org.apache.commons:commons-lang3:3.4")
        compile("org.flywaydb:flyway-core:4.0")
    }

    dependenciesTest {
        compile("org.testng:testng:6.9.5")
    }

    assemble {
        jar {
            fatJar=true
        }
    }
}
