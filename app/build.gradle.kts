import extension.addAndroidTestDependency
import extension.addCommonDependency
import extension.addComposeDependency
import extension.addDestinationDependency
import extension.addHiltDependency
import extension.addModule
import extension.addUnitTestDependency

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = Config.id
    compileSdk = 34

    defaultConfig {
        applicationId = Config.id
        minSdk = Config.minSdk
        targetSdk = Config.maxSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.androidJunitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.composeCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

android.applicationVariants.all {
    val variantName = name
    kotlin.sourceSets{
        getByName("main") {
            kotlin.srcDir(File("build/generated/ksp/$variantName/kotlin"))
        }
    }
}


ksp{
    arg("compose-destinations.generateNavGraphs", "false")
}


dependencies {

    addModule()
    addCommonDependency()
    addComposeDependency()
    addHiltDependency()
    addDestinationDependency()
    addAndroidTestDependency()
    addUnitTestDependency()
}