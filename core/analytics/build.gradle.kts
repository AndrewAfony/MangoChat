plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.analytics"
}

dependencies {

    api("com.jakewharton.timber:timber:5.0.1")
}