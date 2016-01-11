import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.kotlin.*

val repos = repos()

val p = javaProject {
    name = "mixed-example-java"
    group = "com.guatec"
    artifactId = name
    version = "0.1"

    dependenciesTest {
        compile("org.testng:testng:6.9.5")
    }
}

val k = kotlinProject(p) {
    name = "mixed-example"

    dependenciesTest {
        compile("org.testng:testng:6.9.5")
    }

    assemble {
        jar {
        }
    }
}
