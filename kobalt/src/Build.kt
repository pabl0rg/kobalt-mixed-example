import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.kotlin.*
import com.beust.kobalt.maven.DependencyManager

val repos = repos("https://dl.bintray.com/kmruiz/maven")

val p = project {
    name = "mixed-example"
    group = "com.guatec"
    artifactId = name
    version = "0.1"

    dependencies {
        compile(file("lib/jrobin-1.5.9.jar"))
        compile("log4j:log4j:1.2.17")
        compile("com.squareup.okio:okio:1.6.0")
        compile("com.squareup.okhttp:okhttp:2.7.4")
        compile("org.apache.commons:commons-lang3:3.4")
        compile("org.flywaydb:flyway-core:4.0")
    }

    dependenciesTest {
        compile("org.testng:testng:6.9.5")
    }

    test {
        val quasarDep = DependencyManager.create("co.paralleluniverse:quasar-core:0.7.5")
        jvmArgs("-javaagent:${quasarDep.jarFile.get()}", "-Dco.paralleluniverse.fibers.verifyInstrumentation")
    }

    assemble {
        jar {
            fatJar=true
        }
    }
}
