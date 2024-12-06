plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.designsystem"
}

dependencies {

    api(libs.androidx.material3.android)

    implementation(libs.androidx.ui.tooling.preview.android)
    debugImplementation(libs.ui.tooling)
}