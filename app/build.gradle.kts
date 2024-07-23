plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id("org.jetbrains.kotlin.kapt") version "2.0.0"
    id("com.google.dagger.hilt.android") version "2.51.1"
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
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Modules
    implementation(project(":database"))
    implementation(project(":repositories"))
    implementation(project(":ui:navigation"))
    implementation(project(":ui:home"))
    implementation(project(":ui:details"))
    implementation(project(":uitheme"))

    // Dagger
    implementation("com.google.dagger:hilt-android:2.51.1")
    //implementation("androidx.compose.ui:ui-test-junit4-android:1.6.8")   ???
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    //kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")


    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.material3:material3-window-size-class")

    //implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    /*
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

     */
}
