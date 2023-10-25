import extension.MODEL
import extension.addCommonDependency
import extension.addDestinationDependency

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fikrisandi.provider"

}

dependencies {
    MODEL

    addDestinationDependency()
}