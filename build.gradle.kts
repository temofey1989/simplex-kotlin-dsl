import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import java.time.Duration

plugins {
    kotlin("jvm") version "1.8.0"
    `java-library`
    `maven-publish`
    signing
    jacoco
    id("org.sonarqube") version "3.5.0.2730"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "io.justdevit.math"
version = "0.2.0"

repositories {
    mavenCentral()
}

java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.commons:commons-math3:3.6.1")

    testCompileOnly("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")

    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("io.mockk:mockk:1.13.3")

}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = java.sourceCompatibility.toString()
        }
    }

    test {
        useJUnitPlatform()
        testLogging {
            events(PASSED, FAILED, SKIPPED)
        }
    }

    jacocoTestReport {
        reports {
            xml.required.set(true)
        }
    }

    withType<Sign> {
        onlyIf { !project.version.toString().endsWith("SNAPSHOT") }
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "temofey1989_simplex-kotlin-dsl")
        property("sonar.organization", "temofey1989")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set("${project.group}:${project.name}")
                description.set("Simple state machine library with Kotlin DSL.")
                url.set("https://github.com/temofey1989/simplex-kotlin-dsl")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("temofey1989")
                        name.set("Artyom Gornostayev")
                        email.set("temofey1989@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/temofey1989/simplex-kotlin-dsl")
                    connection.set("scm:git:git://github.com/temofey1989/simplex-kotlin-dsl.git")
                    developerConnection.set("scm:git:ssh://github.com:temofey1989/simplex-kotlin-dsl.git")
                }
            }
            suppressPomMetadataWarningsFor("runtimeElements")
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}

nexusPublishing {
    transitionCheckOptions {
        maxRetries.set(100)
        delayBetween.set(Duration.ofSeconds(5))
    }
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
