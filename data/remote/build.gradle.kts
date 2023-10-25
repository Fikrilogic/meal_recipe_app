import extension.FRAMEWORK
import extension.MODEL
import extension.PROVIDER
import extension.addCommonDependency
import extension.addNetworkDependency

plugins {
    id("commons.android-library")
}

android {
    namespace = "com.fikrisandi.remote"

}

dependencies {
    PROVIDER

    FRAMEWORK
    MODEL

    addNetworkDependency()
}