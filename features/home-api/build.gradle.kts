plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "andrewafony.testapp.home_api"
}

dependencies {
    api(projects.core.featureApi)
}