plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("8.1.0").apply(false)
    kotlin("multiplatform").version("1.9.20").apply(false)
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.9.20" apply false
    id("com.jfrog.artifactory") version "4.13.0" apply false
    id("co.touchlab.kmmbridge") version "0.5.1" apply false
    id("co.touchlab.skie") version "0.5.5" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val autoVersion = project.property(
    if (project.hasProperty("AUTO_VERSION")) {
        "AUTO_VERSION"
    } else {
        "LIBRARY_VERSION"
    }
) as String

subprojects {
    val GROUP: String by project
    group = GROUP
    version = autoVersion
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
