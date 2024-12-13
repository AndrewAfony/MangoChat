plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.domain"
}

dependencies {

    implementation(projects.core.designsystem)
    implementation(libs.kotlinx.serialization.json)

    // ==== Koin ====
    implementation (libs.koin.androidx.compose)
}