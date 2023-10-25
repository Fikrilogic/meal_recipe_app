package commons

import extension.addHiltDependency
import extension.implementation
import org.gradle.kotlin.dsl.dependencies

plugins{
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    addHiltDependency()
}