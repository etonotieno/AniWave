plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("com.diffplug.spotless") version "6.21.0"
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        config.setFrom("${project.rootDir}/detekt.yml")
        parallel = true
        buildUponDefaultConfig = true
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint("0.50.0").userData(mapOf("android" to "true"))
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
        }
    }

    dependencies {
        detektPlugins("io.nlopez.compose.rules:detekt:0.2.1")
    }
}
