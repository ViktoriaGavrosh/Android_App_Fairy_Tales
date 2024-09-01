plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.10"
    id("org.jetbrains.kotlin.kapt") version "2.0.10"
    id("com.google.dagger.hilt.android") version "2.52"
}

android {
    namespace = "com.viktoriagavrosh.navigation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.08.00"))
    implementation("androidx.compose.material3:material3-window-size-class")

    // Modules
    implementation(project(":repositories"))
    implementation(project(":ui:home"))
    implementation(project(":ui:details"))
    implementation(project(":ui:settings"))
    implementation(project(":uitheme"))

    // Dagger
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.52")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.52")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.7")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.08.00"))
}
