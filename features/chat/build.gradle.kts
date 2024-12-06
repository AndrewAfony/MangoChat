plugins {
    alias(libs.plugins.mango.feature.plugin)

}

android {
    namespace = "andrewafony.testapp.chat"

}

dependencies {

    implementation(projects.core.designsystem)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)

    // ==== Coil ====
    implementation(libs.coil.compose)
}