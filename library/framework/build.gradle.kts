import extension.PROVIDER
import extension.addHiltDependency
import extension.addNetworkDependency
import extension.addRoomDependency

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fikrisandi.framework"

}

dependencies {
    addHiltDependency()
    addNetworkDependency()
    addRoomDependency()
    implementation(Paging3Libs.runtime)

    PROVIDER
}