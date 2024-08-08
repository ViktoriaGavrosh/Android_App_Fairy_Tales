plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
}
