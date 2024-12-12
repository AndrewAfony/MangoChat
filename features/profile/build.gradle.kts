plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.profile"

}

dependencies {

    implementation(projects.core.designsystem)
    implementation(projects.domain)
    api(projects.features.profileApi)
    implementation(projects.core.common)

    implementation(libs.compose.shimmer)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    // ==== Coil ====
    implementation(libs.coil.compose)
}