plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "andrewafony.testapp.home_api"
}

dependencies {

    api(projects.core.featureApi)
}