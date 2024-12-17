plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.feature_api"
}

dependencies {

    api(libs.androidx.navigation.common.ktx)
    api(libs.androidx.navigation.runtime.ktx)
    api(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.android)

    // ==== Koin ====
    api (libs.koin.androidx.compose)
}