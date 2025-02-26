plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_COMPOSE) version "2.1.0"
}

apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.domain"
}

dependencies {
    androidx()
    hilt()
    room()
    testImpl()
    androidTest()
    debugImpl()
}
