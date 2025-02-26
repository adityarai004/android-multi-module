plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    api(kotlin("gradle-plugin:2.1.0"))
    implementation("com.android.tools.build:gradle:8.8.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
    implementation("com.squareup:javapoet:1.13.0")
}