import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

val commonsMath3: String by project
val kotestVersion: String by project
val mockkVersion: String by project

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.commons:commons-math3:$commonsMath3")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks {
    withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-Xcontext-receivers",
            )
            jvmTarget = JVM_17
        }
    }

    test {
        useJUnitPlatform()
        testLogging {
            events(PASSED, FAILED, SKIPPED)
        }
    }
}
