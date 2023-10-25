object SupportLibs {
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleRuntime}"
    const val viewmodelRuntime = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleRuntime}"
    const val CoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
    const val CoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"
    const val Material = "com.google.android.material:material:1.11.0-alpha01"
    const val ActivityKtx = "androidx.activity:activity-ktx:1.4.0"
    const val datetimeKtx = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
    const val splashscreen = "androidx.core:core-splashscreen:${Version.splash}"
    const val youtube_player = "com.pierfrancescosoffritti.androidyoutubeplayer:core:12.0.0"
    const val coil = "io.coil-kt:coil-compose:2.4.0"
    const val coil_video = "io.coil-kt:coil-video:2.4.0"
}

object ComposeLibs {
    const val ui = "androidx.compose.ui:ui:${Version.compose}"
    const val bom = "androidx.compose:compose-bom:2023.08.00"
    const val constraint = "androidx.constraintlayout:constraintlayout-compose:${Version.constrainCompose}"
    const val activity = "androidx.activity:activity-compose:${Version.activityCompose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Version.compose}"
    const val uiPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
    const val material3 = "androidx.compose.material3:material3:${Version.material3}"
    const val material = "androidx.compose.material:material:${Version.material}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Version.compose}"
}

object ComposeDestinationLibs{
    const val composeDestination = "io.github.raamcosta.compose-destinations:core:${Version.destination}"
    const val composeDestinationKsp = "io.github.raamcosta.compose-destinations:ksp:${Version.destination}"
}

object StorageLibs{
    const val roomRuntime = "androidx.room:room-runtime:${Version.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room_version}"
    const val store = "androidx.datastore:datastore:${Version.datastore_version}"
    const val storePref = "androidx.datastore:datastore-preferences:${Version.datastore_version}"
}

object DaggerHiltLibs {
    const val android = "com.google.dagger:hilt-android:${Version.daggerHilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
    const val compose = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object Paging3Libs{
    const val runtime = "androidx.paging:paging-runtime:${Version.paging3}"
    const val jetpack = "androidx.paging:paging-compose:3.2.0"
}

object NetworkLibs{
    const val ktor = "io.ktor:ktor-client-core:${Version.ktor}"
    const val ktor_android_engine = "io.ktor:ktor-client-android:${Version.ktor}"
    const val ktor_logging = "io.ktor:ktor-client-logging:${Version.ktor}"
    const val ktor_gson = "io.ktor:ktor-serialization-gson:${Version.ktor}"
    const val ktor_content_negotiation = "io.ktor:ktor-client-content-negotiation:${Version.ktor}"
    const val ktor_auth = "io.ktor:ktor-client-auth:${Version.ktor}"
    const val ktor_resource = "io.ktor:ktor-client-resources:${Version.ktor}"
}

object TestLibs {
    const val jUnit = "junit:junit:${Version.jUnit}"
    const val jUnitAndroid = "androidx.test.ext:junit:${Version.jUnitAndroid}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Version.compose}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
}