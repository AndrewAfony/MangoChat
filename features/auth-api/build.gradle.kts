plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.auth_api"

}

dependencies {
    api(projects.core.featureApi)
}