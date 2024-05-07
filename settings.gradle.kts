pluginManagement {
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

// Generate type safe accessors when referring to other projects eg.
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "app-challenge"
include(":app")
include(":core_design_system")
include(":core_feature_arch")
include(":plugin_data_privacy")
include(":feature_virtual_cost")
include(":feature_virtual_cost:presentation")
include(":feature_virtual_cost:data")
include(":feature_virtual_cost:domain")
