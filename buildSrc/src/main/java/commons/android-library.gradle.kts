package commons

import com.android.build.api.dsl.BuildType
import extension.addAndroidTestDependency
import extension.addCommonDependency
import extension.addUnitTestDependency


import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android{
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        testInstrumentationRunner = Config.androidJunitRunner

    }

    buildFeatures.buildConfig = true

    buildTypes {

//        debug {
//            buildConfigStringField("DB_NAME", Config.Debug.DbName)
//            buildConfigStringField("DATASTORE_NAME", Config.Debug.DatastoreName)
//        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled  = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}


android.libraryVariants.all {
    val variantName = name
    kotlin.sourceSets {
        getByName("main") {
            kotlin.srcDir(File("build/generated/ksp/$variantName/kotlin"))
        }
    }
}

fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.2.2")
    addCommonDependency()
    addUnitTestDependency()
    addAndroidTestDependency()
}