import extension.THEME
import extension.addCommonDependency

plugins {
    id("commons.android-library")
    id("commons.android-compose")
}

android {
    namespace = "com.fikrisandi.component"

}

dependencies {

    THEME
}