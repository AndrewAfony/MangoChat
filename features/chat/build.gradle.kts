plugins {
    alias(libs.plugins.mangochat.android.feature)
    alias(libs.plugins.mangochat.android.library.compose)
}

android {
    namespace = "andrewafony.testapp.chat"

}

dependencies {

    api(projects.features.chatApi)

    implementation(projects.domain)

    // ==== Coil ====
    implementation(libs.coil.compose)
}