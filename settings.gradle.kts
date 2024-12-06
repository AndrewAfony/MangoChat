pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MangoTestChat"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":data")
include(":domain")

include(":features:home")
include(":features:profile")
include(":features:chat")
include(":features:auth")

include(":core:designsystem")
