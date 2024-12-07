plugins {
    alias(libs.plugins.mango.feature.plugin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "andrewafony.testapp.data"
}

dependencies {

    implementation(projects.domain)

    // ==== Room ====
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // ==== Koin ====
    implementation (libs.koin.androidx.compose)
}