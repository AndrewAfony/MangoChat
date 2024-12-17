plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "andrewafony.testapp.shared_data"
}

dependencies {

    implementation(projects.domain)
    implementation(projects.core.analytics)

    implementation(libs.androidx.material3.android)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.retrofit)
}