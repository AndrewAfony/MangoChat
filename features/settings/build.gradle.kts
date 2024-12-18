plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.settings"

}

dependencies {

    api(projects.features.settingsApi)

    implementation(projects.domain)

    implementation(projects.core.designsystem)
    implementation(projects.features.profileApi)

    implementation(libs.compose.shimmer)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)

    // ==== Coil ====
    implementation(libs.coil.compose)
    debugImplementation(libs.ui.tooling)
}