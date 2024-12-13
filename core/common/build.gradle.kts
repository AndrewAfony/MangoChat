plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.common"

}

dependencies {
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

}