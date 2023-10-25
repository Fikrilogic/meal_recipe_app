import extension.FRAMEWORK
import extension.MODEL
import extension.REPOSITORY
import extension.addPaging3Dependency

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
}

android {
    namespace = "com.fikrisandi.domain"

}

dependencies {

    FRAMEWORK

    MODEL
    REPOSITORY

    addPaging3Dependency()
}