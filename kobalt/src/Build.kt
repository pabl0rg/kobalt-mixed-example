import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.kotlin.*

val repos = repos()

val p = javaProject {
    name = "mixed-example"
    group = "com.guatec"
    artifactId = name
    version = "0.1"
    buildDirectory = "javaBuild"

    sourceDirectories {
        path("src/main/java", "src/main/kotlin")
    }

    sourceDirectoriesTest {
        path("src/test/java", "src/test/kotlin")
    }

    dependencies {
        compile("log4j:log4j:1.2.17")
    }

    dependenciesTest {
        compile("org.testng:testng:6.9.5")
    }

    assemble {
        jar {
        }
    }
}
