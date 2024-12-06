plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.profile_api"
}

dependencies {
    api(projects.core.featureApi)
}