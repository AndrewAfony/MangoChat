plugins {
    alias(libs.plugins.mangochat.android.feature)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.profile"

}

dependencies {

    api(projects.features.profileApi)

    implementation(projects.domain)
    implementation(projects.core.shared.sharedUi)

    implementation(libs.compose.shimmer)

    // ==== Coil ====
    implementation(libs.coil.compose)
}