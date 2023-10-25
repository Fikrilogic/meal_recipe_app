package commons

import com.android.build.api.dsl.BuildType
import extension.addComposeDependency
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler


plugins{
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.composeCompiler
    }

    packaging {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }

}

fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

dependencies {
    addComposeDependency()
}