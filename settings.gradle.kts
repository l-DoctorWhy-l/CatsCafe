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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "cats-cafe"

include(":app")

// common
include(":common:common-ui")
include(":common:common-utils")

// core
include(":core:core-network:core-network-api")
include(":core:core-network:core-network-impl")
include(":core:core-database:core-database-api")
include(":core:core-database:core-database-impl")
include(":core:core-navigation:core-navigation-api")
include(":core:core-navigation:core-navigation-impl")

// feature
// Шаблон: include(":feature:<screen>:<screen>-api")
//         include(":feature:<screen>:<screen>-impl")
