package andrewafony.testapp.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            pluginManager.apply {
                apply("mangochat.android.library")
            }

            dependencies {
                add("implementation", project(":core:designsystem"))
            }
        }
    }
}