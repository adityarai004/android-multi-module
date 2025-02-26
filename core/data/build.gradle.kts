plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_COMPOSE) version "2.1.0"
}

apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.data"
}

dependencies {
    okHttp()
    retrofit()
    hilt()
    testImpl()
    androidTest()
    debugImpl()
}
