import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.room() {
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    kapt(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.RETROFIT_KOTLIN_COROUTINES_ADAPTER)
}

fun DependencyHandler.okHttp() {
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_COMPILER)
}

fun DependencyHandler.androidx() {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ACTIVITY_COMPOSE)
//    implementation(platform(libs.androidx.compose.bom))
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_UI_GRAPHICS)
    implementation(Dependencies.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Dependencies.MATERIAL3)
}

fun DependencyHandler.loginModule(){
    moduleImplementation(project(":features:login"))
}

fun DependencyHandler.homeModule(){
    moduleImplementation(project(":features:home"))
}

fun DependencyHandler.testImpl(){
    testImplementation(TestDependencies.JUNIT)
}

fun DependencyHandler.androidTest(){
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO_CORE)
    androidTestImplementation(TestDependencies.UI_TEST_JUNIT4)
}

fun DependencyHandler.debugImpl(){
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING_PREVIEW)
    debugImplementation(TestDependencies.UI_TEST_MANIFEST)
}