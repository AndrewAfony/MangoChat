plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.designsystem"
}

dependencies {

    api(libs.androidx.material3.android)

    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.navigation.common.ktx)
    debugImplementation(libs.ui.tooling)
}