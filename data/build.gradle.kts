plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secrets)
}

android {
    namespace = "andrewafony.testapp.data"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(projects.domain)

    implementation(projects.core.analytics)
    implementation(projects.core.common)

    // ==== Retrofit ====
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.okhttp)
    implementation (libs.logging.interceptor)

    // ==== Room ====
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // ==== DataStore ====
    implementation (libs.androidx.datastore.preferences)

    // ==== Koin ====
    implementation (libs.koin.androidx.compose)
}