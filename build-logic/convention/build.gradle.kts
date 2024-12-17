import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.build.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "mangochat.android.application.compose"
            implementationClass = "andrewafony.testapp.convention.AndroidApplicationComposePlugin"
        }
        register("androidApplication") {
            id = "mangochat.android.application"
            implementationClass = "andrewafony.testapp.convention.AndroidApplicationPlugin"
        }
        register("androidFeature") {
            id = "mangochat.android.feature"
            implementationClass = "andrewafony.testapp.convention.AndroidFeaturePlugin"
        }
        register("androidLibrary") {
            id = "mangochat.android.library"
            implementationClass = "andrewafony.testapp.convention.AndroidLibraryPlugin"
        }
        register("androidLibraryCompose") {
            id = "mangochat.android.library.compose"
            implementationClass = "andrewafony.testapp.convention.AndroidLibraryComposePlugin"
        }
    }
}