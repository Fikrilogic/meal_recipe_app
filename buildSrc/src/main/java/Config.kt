object Config {

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private fun generateVersionCode(): Int {
        return versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun generateVersionName(): String {
        return "$versionMajor.$versionMinor.$versionPatch"
    }

    const val id = "com.fikrisandi.recipeApp"
    const val minSdk = 21
    const val maxSdk = 34
    const val compileSdk = 34
    val versionCode = generateVersionCode()
    val versionName = generateVersionName()

    const val androidJunitRunner =  "androidx.test.runner.AndroidJUnitRunner"

    val FreeCompilerArgs = listOf(
        "-Xjvm-default=all",
        "opt-in=kotlin.RequiresOptIn",
        "opt-in=kotlin.Experimental",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview",
        "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
        "-Xopt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
        "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
    )

    val FreeCoroutineCompilerArgs = listOf(
        "-Xjvm-default=all",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.Experimental",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview"
    )

    object Release{
    }

    object Debug{
    }
}