import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("maven-publish")
    id("com.google.dagger.hilt.android")
    id("com.jfrog.artifactory")
    id("co.touchlab.kmmbridge")
    id("co.touchlab.skie")
}

group = "me.sokolovavita.kmp-shared" // Change this to your group ID
version = "1.0.0" // Change this to your version

artifactory {
    val artifactoryContextUrl: String = gradleLocalProperties(rootDir).getProperty("artifactory_contextUrl")
    val artifactoryUser: String = gradleLocalProperties(rootDir).getProperty("artifactory_user")
    val artifactoryPassword: String = gradleLocalProperties(rootDir).getProperty("artifactory_password")
    setContextUrl(artifactoryContextUrl)
    publish(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig> {
        repository(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.DoubleDelegateWrapper> {
            setProperty("repoKey", "gradle-dev-local")
            setProperty("username", artifactoryUser)
            setProperty("password", artifactoryPassword)
            setProperty("maven", true)
        })
        defaults(delegateClosureOf<groovy.lang.GroovyObject> {
            invokeMethod("publications", arrayOf(
                "androidDebug", "androidRelease", "kotlinMultiplatform", "metadata"
            ))
            invokeMethod("publishPom", true)
        })
    })
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    jvmToolchain(17)

    targetHierarchy.default()

    androidTarget {

        publishLibraryVariants("release", "debug")

        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
//            export(project(":shared"))
            isStatic = true
        }
    }

    val ktorVersion = "2.3.2"
    val koinVersion = "3.5.0"
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.insert-koin:koin-android:$koinVersion")
                implementation("com.google.dagger:hilt-android:2.46.1")
                configurations.getByName("kapt").dependencies.add(
                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "hilt-compiler",
                        "2.46.1"
                    )
                )
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "vita.sokolova.testlibrary"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

addGithubPackagesRepository()

kmmbridge {
    mavenPublishArtifacts()
    spm()
}
