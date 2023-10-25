import extension.FRAMEWORK
import extension.MODEL
import extension.addRoomDependency

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
    id("commons.dagger-hilt")
}

android {
    namespace = "com.fikrisandi.local"
}


dependencies {
    FRAMEWORK
    MODEL

    addRoomDependency()
}