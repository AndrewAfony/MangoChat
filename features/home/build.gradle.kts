plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.home"
}

dependencies {

    implementation(projects.core.designsystem)
    api(projects.features.homeApi)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)

    // ==== Coil ====
    implementation(libs.coil.compose)
}