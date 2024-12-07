plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.chat"

}

dependencies {

    implementation(projects.domain)
    implementation(projects.core.designsystem)
    api(projects.features.chatApi)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)

    // ==== Coil ====
    implementation(libs.coil.compose)
}