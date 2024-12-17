plugins {
    alias(libs.plugins.mangochat.android.library)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.shared_ui"
}

dependencies {

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(projects.core.designsystem)

    implementation(libs.compose.shimmer)
}