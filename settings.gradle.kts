pluginManagement {
    repositories {
        google()
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
rootProject.name = "MealRecipeApp"
include(":app")
include(":commons:theme")
include(":commons:provider")
include(":commons:component")
include(":data:model")
include(":data:repository")
include(":data:remote")
include(":data:local")
include(":domain")
include(":library:framework")
include(":feature:home")
include(":feature:recipe")
