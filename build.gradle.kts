buildscript{
    dependencies{
        classpath("com.android.tools.build:gradle:8.1.1")
    }
}


plugins{
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}