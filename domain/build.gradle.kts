plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.domain"

}

dependencies {

    implementation(projects.core.designsystem)

    // ==== Koin ====
    implementation (libs.koin.androidx.compose)
}