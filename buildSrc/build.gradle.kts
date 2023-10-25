plugins{
    `kotlin-dsl`
}

repositories{
    mavenCentral()
    gradlePluginPortal()
    google()
}

gradlePlugin{
    plugins{
        register("AndroidCoreLibraryPlugin") {
            id = "android.core.library.plugin"
            implementationClass = "commons.AndroidCoreLibraryPlugin"
        }
    }
}

object Version{
    const val GRADLE = "8.1.1"
    const val KOTLIN = "1.9.0"
    const val HILT = "2.46.1"
    const val FIREBASE = "32.2.3"

}

object Deps {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Version.GRADLE}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
}

dependencies {
    implementation(Deps.ANDROID_GRADLE)
    implementation(Deps.KOTLIN_GRADLE)
    implementation(Deps.HILT)
}