plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
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