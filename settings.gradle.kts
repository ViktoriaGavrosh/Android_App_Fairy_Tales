pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FairyTales"
include(":app")
include(":database")
include(":datastore")
include(":repositories")
include(":ui:home")
include(":ui:navigation")
include(":ui:details")
include(":ui:settings")
include(":uitheme")


