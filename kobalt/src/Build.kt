import com.beust.kobalt.*
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Task
import com.beust.kobalt.maven.DependencyManager
import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.kotlin.kotlinCompiler
import com.beust.kobalt.plugin.packaging.assemble
import java.io.File
import java.nio.charset.Charset

val bs = buildScript {
    plugins("net.thauvin.erik:kobalt-maven-local:")
}

@Task(name = "createVersion", reverseDependsOn = arrayOf("compile", "test", "assemble"), runAfter = arrayOf("clean"))
fun taskCreateVersion(project: Project) : TaskResult {

    val gitLog = File(".git/logs/HEAD").readLines(Charset.forName("UTF-8"))
    val gitHash = gitLog[gitLog.lastIndex].split(" ")[1]

    val path = "com/guatec/internal"
    with(arrayListOf<String>()) {
        File("src/main/resources/$path/VersionTemplate.txt").forEachLine {
            add(it.replace("@version@", project.version!!).replace("@gitHash@", gitHash))
        }
        File("src/generated/java/$path/Version.java").writeText(joinToString("\n"))
    }
    return TaskResult()
}

val p = project {
    name = "mixed-example"
    group = "com.guatec"
    artifactId = name
    version = "1.12"

    sourceDirectories {
        path("src/main/java", "src/generated/java")
    }

    kotlinCompiler {
        version = "1.3.21"
        args("-jvm-target", "1.8")
    }

    dependencies {
        exclude("org.jetbrains.kotlin:kotlin-reflect:1.0.6", "org.jetbrains.kotlin:kotlin-runtime:1.0.6")
        compile("org.jetbrains.kotlin:kotlin-reflect:1.1.2", "org.jetbrains.kotlin:kotlin-stdlib-jre8:1.1.2")

        compile(file("lib/jrobin-1.5.9.jar"))
        compile("log4j:log4j:1.2.17")
        compile("org.apache.commons:commons-lang3:3.4")
        compile("com.h2database:h2:jar:1.4.192")
        exclude("org.jetbrains.kotlin:kotlin-reflect:1.0.6")
        compile("com.h2database:h2:1.4.192",
                "org.flywaydb:flyway-core:4.1.2",
                "com.squareup.okhttp:okhttp:jar:2.7.5",
                "com.squareup.retrofit2:retrofit:2.1.0",
                "com.squareup.retrofit2:converter-jackson:2.1.0",
                "com.squareup.okhttp3:okhttp:3.2.0",
                "com.squareup.okio:okio:1.6.0",
                "org.twitter4j:twitter4j-core:3.0.4",
                "org.twitter4j:twitter4j-async:3.0.4",
                "org.jetbrains.kotlin:kotlin-reflect:1.1.0",
                "org.jetbrains.kotlin:kotlin-stdlib-jre8:1.1.1",
                "com.fasterxml.jackson.core:jackson-databind:2.8.7",
                "com.fasterxml.jackson.core:jackson-core:2.8.7",
                "com.fasterxml.jackson.core:jackson-annotations:2.8.7",
                "com.fasterxml.jackson.module:jackson-module-kotlin:2.8.7")
    }

    dependenciesTest {
        compile("org.testng:testng:6.9.5")
    }

    test {
        val quasarDep = DependencyManager.create("co.paralleluniverse:quasar-core:0.7.5")
        jvmArgs("-javaagent:${quasarDep.jarFile.get()}", "-Dco.paralleluniverse.fibers.verifyInstrumentation")
    }
}

val dependent = project(p) {
    name = "depenent-in-same-dir"
    group = "com.guatec"
    artifactId = name
    version = "1.12"

    sourceDirectories {
        path("src/main/java", "src/generated/java")
    }

    assemble {
        jar {
            fatJar=true
        }
    }

    application {
        mainClass = "com.guatec.Example"
    }
}
