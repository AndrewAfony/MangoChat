plugins {
    alias(libs.plugins.mangochat.android.feature)
    alias(libs.plugins.mangochat.android.library.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "andrewafony.testapp.auth"
}

dependencies {

    api(projects.features.authApi)

    implementation(projects.domain)
    implementation(projects.core.shared.sharedUi)

    implementation(projects.features.homeApi)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.country.code.picker)
}