plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.home"
}

dependencies {

    api(projects.features.homeApi)

    implementation(projects.domain)
    implementation(projects.features.chatApi)

    implementation(projects.core.common)
    implementation(projects.core.designsystem)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)

    // ==== Coil ====
    implementation(libs.coil.compose)
    implementation(libs.coil.network)
}