buildscript {
    dependencies {
        classpath(libs.gradleTools)
        classpath(libs.googleServicesClasspath)
        classpath(libs.firebaseCrashlyticsGradleClasspath)
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply(false)
    alias(libs.plugins.kotlinAndroid) apply(false)
    alias(libs.plugins.daggerHiltAndroid) apply(false)
}