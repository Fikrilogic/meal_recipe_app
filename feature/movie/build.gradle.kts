import extension.COMPONENT
import extension.DOMAIN
import extension.FRAMEWORK
import extension.MODEL
import extension.PROVIDER
import extension.THEME
import extension.addDestinationDependency
import extension.addHiltDependency
import extension.addPaging3Dependency

plugins {
    id("commons.android-feature")
    id("commons.android-compose")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fikrisandi.movie"
}

ksp{
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "movie")
}

dependencies {
    THEME
    PROVIDER
    COMPONENT

    FRAMEWORK

    MODEL
    DOMAIN

    addHiltDependency()
    addDestinationDependency()
    addPaging3Dependency()

    implementation(SupportLibs.coil)
    implementation(SupportLibs.youtube_player)
}