plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.viktoriagavrosh.fairytales"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.viktoriagavrosh.fairytales"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Coil
    implementation(libs.coil.compose)

    // Modules
    implementation(projects.database)
    implementation(projects.datastore)
    implementation(projects.repositories)
    implementation(projects.ui.navigation)
    implementation(projects.ui.shelf)
    implementation(project(":ui:reader"))
    //implementation(projects.ui.read)   TODO 111
    //implementation(projects.ui.riddle)   TODO 111
    //implementation(projects.ui.startmenu)   TODO 111
    implementation(project(":ui:riddle"))
    implementation(project(":ui:startmenu"))
    implementation(project(":ui:librarymenu"))
    implementation(project(":ui:infomenu"))
    implementation(project(":ui:addtale"))
    implementation(projects.ui.settings)
    implementation(projects.uitheme)

    // Dagger
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.material3.window.size)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
