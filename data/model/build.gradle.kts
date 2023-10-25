import extension.addRoomDependency

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fikrisandi.model"

}

dependencies {

    addRoomDependency()
    implementation(NetworkLibs.ktor_gson)
}