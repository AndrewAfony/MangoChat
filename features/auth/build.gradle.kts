plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "andrewafony.testapp.auth"
}

dependencies {

    api(projects.features.authApi)

    implementation(projects.domain)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)

    implementation(projects.features.homeApi)

    implementation(libs.kotlinx.serialization.json)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    implementation(libs.country.code.picker)
}