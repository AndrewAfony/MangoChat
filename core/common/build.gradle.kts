plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "andrewafony.testapp.common"

}

dependencies {

    implementation(projects.domain)
    implementation(projects.core.analytics)

    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
}