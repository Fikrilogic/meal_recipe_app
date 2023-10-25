package extension

import ComposeLibs
import DaggerHiltLibs
import NetworkLibs
import SupportLibs
import Paging3Libs
import TestLibs
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler


/**
 * Adds a dependency to the `releaseImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.releaseImplementation(dependencyNotation: Any): Dependency? =
    add("releaseImplementation", dependencyNotation)

/**
 * Adds a dependency to the `debugImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)


/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the `ksp` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.ksp(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)


fun DependencyHandler.addCommonDependency() {
    implementation(SupportLibs.coreKtx)
    implementation(SupportLibs.lifecycleRuntime)
    implementation(SupportLibs.viewmodelRuntime)
    implementation(SupportLibs.CoroutineAndroid)
    implementation(SupportLibs.CoroutineCore)
    implementation(SupportLibs.CoroutineCore)
    implementation(SupportLibs.datetimeKtx)

}

fun DependencyHandler.addUiDependency(){
    implementation(SupportLibs.Material)
    implementation(SupportLibs.ActivityKtx)
}

fun DependencyHandler.addComposeDependency() {
    implementation(ComposeLibs.ui)
    implementation(ComposeLibs.uiGraphics)
    implementation(ComposeLibs.uiPreview)
    implementation(platform(ComposeLibs.bom))
    implementation(ComposeLibs.material3)
    implementation(ComposeLibs.material)
    debugImplementation(ComposeLibs.uiTooling)
    debugImplementation(ComposeLibs.uiTestManifest)
    implementation(ComposeLibs.activity)
    implementation(ComposeLibs.constraint)
    androidTestImplementation(TestLibs.composeJunit)
}

fun DependencyHandler.addUnitTestDependency() {
    testImplementation(TestLibs.jUnit)
}

fun DependencyHandler.addAndroidTestDependency() {
    androidTestImplementation(TestLibs.espresso)
    androidTestImplementation(TestLibs.jUnitAndroid)
}

fun DependencyHandler.addHiltDependency() {
    implementation(DaggerHiltLibs.android)
    implementation(DaggerHiltLibs.compose)
    kapt(DaggerHiltLibs.compiler)
}

fun DependencyHandler.addPaging3Dependency(){
    implementation(Paging3Libs.runtime)
    implementation(Paging3Libs.jetpack)
}

fun DependencyHandler.addRoomDependency(){
    implementation(StorageLibs.roomRuntime)
    implementation(StorageLibs.roomKtx)
    ksp(StorageLibs.roomCompiler)
}

fun DependencyHandler.addDatastoreDependency() {
    implementation(StorageLibs.store)
    implementation(StorageLibs.storePref)
}

fun DependencyHandler.addNetworkDependency(){
    implementation(NetworkLibs.ktor)
    implementation(NetworkLibs.ktor_android_engine)
    implementation(NetworkLibs.ktor_logging)
    implementation(NetworkLibs.ktor_gson)
    implementation(NetworkLibs.ktor_content_negotiation)
    implementation(NetworkLibs.ktor_auth)
    implementation(NetworkLibs.ktor_resource)
}

fun DependencyHandler.addDestinationDependency() {
    implementation(ComposeDestinationLibs.composeDestination)
    ksp(ComposeDestinationLibs.composeDestinationKsp)
}

fun DependencyHandler.addModule(){

    FRAMEWORK

    THEME
    PROVIDER
    COMPONENT

    MODEL
    LOCAL
    REPOSITORY

    DOMAIN

    HOME
    RECIPE

}

val DependencyHandler.THEME
    get() = implementation(project(mapOf("path" to ":commons:theme")))
val DependencyHandler.PROVIDER
    get() = implementation(project(mapOf("path" to ":commons:provider")))
val DependencyHandler.COMPONENT
    get() = implementation(project(mapOf("path" to ":commons:component")))

val DependencyHandler.FRAMEWORK
    get() = implementation(project(mapOf("path" to ":library:framework")))

val DependencyHandler.MODEL
    get() = implementation(project(mapOf("path" to ":data:model")))
val DependencyHandler.LOCAL
    get() = implementation(project(mapOf("path" to ":data:local")))
val DependencyHandler.REPOSITORY
    get() = implementation(project(mapOf("path" to ":data:repository")))
val DependencyHandler.DOMAIN
    get() = implementation(project(mapOf("path" to ":domain")))

val DependencyHandler.HOME
    get() = implementation(project(mapOf("path" to ":feature:home")))
val DependencyHandler.RECIPE
    get() = implementation(project(mapOf("path" to ":feature:recipe")))