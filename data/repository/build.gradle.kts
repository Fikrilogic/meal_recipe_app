import extension.FRAMEWORK
import extension.LOCAL
import extension.MODEL
import extension.PROVIDER
import extension.addNetworkDependency

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
}

android {
    namespace = "com.fikrisandi.repository"

}

dependencies {
    PROVIDER

    FRAMEWORK
    MODEL
    LOCAL

    addNetworkDependency()
}