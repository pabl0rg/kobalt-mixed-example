import com.beust.kobalt.*
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Task
import com.beust.kobalt.maven.DependencyManager
import com.beust.kobalt.plugin.packaging.assemble
import java.io.File
import java.nio.charset.Charset

val repos = repos("https://dl.bintray.com/kmruiz/maven",
        "http://repository.jetbrains.com/all",
        "https://dl.bintray.com/cbeust/maven/")

val plugins = plugins("com.beust.kobalt:kobalt-line-count:")

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
    version = "1.1.2"

    sourceDirectories {
        path("src/main/java", "src/generated/java")
    }

    dependencies {
        compile(file("lib/jrobin-1.5.9.jar"))
        compile("log4j:log4j:1.2.17")
        compile("com.squareup.okio:okio:1.6.0")
        compile("com.squareup.okhttp:okhttp:2.7.4")
        compile("org.apache.commons:commons-lang3:3.4")
        compile("com.h2database:h2:jar:1.4.192")
        //compile("org.flywaydb:flyway-core:4.0")
        compile(file("lib/flyway-core-0-SNAPSHOT.jar"))
        compile("org.apache.activemq:activemq-client:5.11.3")
        compile("org.apache.activemq:activemq-pool:5.11.3")
        compile("commons-validator:commons-validator:1.4.0")
        compile("org.apache.commons:commons-email:1.3.2")
        compile("org.twitter4j:twitter4j-core:3.0.4")
        compile("org.twitter4j:twitter4j-async:3.0.4")
        compile("com.fasterxml.jackson.core:jackson-databind:2.7.1")
        compile("com.fasterxml.jackson.core:jackson-core:2.7.1")
        compile("com.fasterxml.jackson.core:jackson-annotations:2.7.1")
        compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.7.1-2")
        compile("org.codehaus.woodstox:woodstox-core-asl:4.4.1")
        compile("joda-time:joda-time:2.2")
        compile("com.squareup.okhttp3:okhttp:3.2.0")
        compile("com.squareup.okio:okio:1.6.0")
        compile("com.squareup.retrofit:converter-jackson:1.9.0")
        compile("com.beust:jcommander:1.48")
        compile("javax.jms:jms:1.1")
        compile("javax.mail:mail:1.4")
        compile("org.postgresql:postgresql:9.2-1004-jdbc41")
        compile("org.zeromq:jeromq:0.3.5")    //inter-process comm
        compile("commons-discovery:commons-discovery:0.4")
        compile("wsdl4j:wsdl4j:1.6.2")
        compile("org.fusesource.hawtbuf:hawtbuf:1.11")
        compile("io.github.lukehutch:fast-classpath-scanner:1.9.18")
        compile("org.fusesource.hawtbuf:hawtbuf:1.11")
        compile("com.timgroup:java-statsd-client:3.1.0")
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
