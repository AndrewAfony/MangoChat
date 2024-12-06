plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.profile"

}

dependencies {

    implementation(projects.core.designsystem)
    api(projects.features.profileApi)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    // ==== Coil ====
    implementation(libs.coil.compose)
}