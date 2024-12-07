plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.auth"
}

dependencies {

    implementation(projects.core.designsystem)
    implementation(projects.features.homeApi)

    api(projects.features.authApi)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    implementation(libs.country.code.picker)
}