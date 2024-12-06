plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.auth"
}

dependencies {

    implementation(projects.core.designsystem)

    api(projects.features.authApi)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    implementation("io.github.joelkanyi:komposecountrycodepicker:1.2.8")
}