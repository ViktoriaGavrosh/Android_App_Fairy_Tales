// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    extra.apply {
        set("room_version", "2.6.1")
    }
}

plugins {
    id("com.android.application") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.10" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.10" apply false
    id("com.android.library") version "8.6.0" apply false
    id("org.jetbrains.kotlin.kapt") version "2.0.10" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
    id("org.jetbrains.kotlin.jvm") version "2.0.10" apply false
}

