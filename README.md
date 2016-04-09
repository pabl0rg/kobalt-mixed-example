# kobalt-mixed-example
an example of a mixed java/kotlin project

I mostly use this to share examples with library or tool maintainers whenever i encounter issues

This project builds with [Kobalt](http://beust.com/kobalt/documentation/index.html) which is a build tool like Gradle, but easier to learn.  The build is configured in kobalt/src/Build.kt which is written in Kotlin, so it is type-safe and has auto-complete support from the IDE.  There is a helpful Kobalt plugin for IntelliJ IDEA that adds a "sync build file" option to the Tools menu.  This option downloads all dependencies and adds them to your project's libraries.
