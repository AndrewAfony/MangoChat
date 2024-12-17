plugins {
    alias(libs.plugins.mangochat.android.feature)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.settings"

}

dependencies {

    api(projects.features.settingsApi)

    implementation(projects.domain)

    implementation(projects.features.profileApi)

    implementation(libs.compose.shimmer)

    // ==== Coil ====
    implementation(libs.coil.compose)
    debugImplementation(libs.ui.tooling)
}