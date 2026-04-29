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
include(":core:core-account:core-account-api")
include(":core:core-account:core-account-impl")

// feature
include(":feature:splash:splash-api")
include(":feature:splash:splash-impl")
include(":feature:auth:auth-api")
include(":feature:auth:auth-impl")
include(":feature:home:home-api")
include(":feature:home:home-impl")
include(":feature:catalog:catalog-api")
include(":feature:catalog:catalog-impl")
include(":feature:booking:booking-api")
include(":feature:booking:booking-impl")
include(":feature:profile:profile-api")
include(":feature:profile:profile-impl")
include(":feature:cat-details:cat-details-api")
include(":feature:cat-details:cat-details-impl")
include(":feature:my-bookings:my-bookings-api")
include(":feature:my-bookings:my-bookings-impl")
include(":feature:donations:donations-api")
include(":feature:donations:donations-impl")
