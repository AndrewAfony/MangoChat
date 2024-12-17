plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
    alias(libs.plugins.serialization)
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