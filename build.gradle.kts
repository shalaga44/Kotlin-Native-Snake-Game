plugins {
    kotlin("multiplatform") version "1.4.0"
}
group = "me.shalaga44"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
kotlin {
    val nativeTarget = when (System.getProperty("os.name")) {
        "Mac OS X" -> macosX64("native")
        "Linux" -> linuxX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        val main by compilations.getting
        val interop by main.cinterops.creating {
            defFile(project.file("ncurses.def"))
        }
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }


    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
}