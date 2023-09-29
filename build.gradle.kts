plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("8.1.1").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.21" apply false
    id("com.jfrog.artifactory") version "4.13.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
