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
    implementation(libs.build.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidPlugin") {
            id = "andrewafony.testApp.plugin.android"
            implementationClass = "AndroidPlugin"
        }
        register("featurePlugin") {
            id = "andrewafony.testApp.plugin.feature"
            implementationClass = "FeaturePlugin"
        }
    }
}