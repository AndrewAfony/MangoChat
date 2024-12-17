plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.analytics"
}

dependencies {

    api(libs.timber)
}