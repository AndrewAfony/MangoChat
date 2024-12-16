plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.shared_ui"
}

dependencies {

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(projects.core.designsystem)

    implementation(libs.compose.shimmer)
}