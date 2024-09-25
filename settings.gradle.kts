enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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
include(":ui:shelf")
include(":ui:navigation")
include(":ui:reader")
include(":ui:settings")
include(":ui:uikit")
include(":uitheme")
include(":ui:riddle")
include(":ui:startmenu")
include(":ui:librarymenu")
include(":ui:infomenu")
include(":ui:addtale")
