plugins {
    alias(libs.plugins.mango.feature.plugin)
}

android {
    namespace = "andrewafony.testapp.designsystem"
}

dependencies {

    api(libs.androidx.material3.android)
}