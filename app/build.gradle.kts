plugins {
    alias(libs.plugins.mango.android.plugin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "andrewafony.testapp.mangotestchat"

    defaultConfig {
        applicationId = "andrewafony.testapp.mangotestchat"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(projects.features.auth)
    implementation(projects.features.home)
    implementation(projects.features.profile)
    implementation(projects.features.chat)
    implementation(projects.features.settings)

    implementation(projects.data)
    implementation(projects.domain)

    implementation(projects.core.featureApi)
    implementation(projects.core.designsystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}