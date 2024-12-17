plugins {
    alias(libs.plugins.mangochat.android.feature)
    alias(libs.plugins.mangochat.android.library.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "andrewafony.testapp.home"
}

dependencies {

    api(projects.features.homeApi)

    implementation(projects.domain)
    implementation(projects.features.chatApi)

    implementation(projects.core.shared.sharedUi)

    // ==== Coil ====
    implementation(libs.coil.compose)
    implementation(libs.coil.network)
}