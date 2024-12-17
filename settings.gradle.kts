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
include(":features:home-api")
include(":features:profile")
include(":features:profile-api")
include(":features:chat")
include(":features:chat-api")
include(":features:auth")
include(":features:auth-api")
include(":features:settings")
include(":features:settings-api")

include(":core:feature-api")
include(":core:designsystem")
include(":core:analytics")
include(":core:shared:shared-data")
include(":core:shared:shared-ui")
