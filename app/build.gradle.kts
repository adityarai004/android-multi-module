plugins {
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_COMPOSE) version "2.1.0"
    id(BuildPlugins.KAPT)
    id(BuildPlugins.KTLINT)
    id(BuildPlugins.HILT) version DependenciesVersion.HILT
//    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = BuildConfig.APP_ID
    compileSdk = BuildConfig.COMPILE_SDK

    defaultConfig {
        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK
        versionCode = ReleaseConfig.VERSION_CODE
        versionName = ReleaseConfig.VERSION_NAME

        testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            isMinifyEnabled = Build.Release.isMinifyEnabled
            isDebuggable = Build.Release.isDebuggable
            enableUnitTestCoverage = Build.Release.enableUnitTestCoverage
        }

        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = Build.Debug.isMinifyEnabled
            isDebuggable = Build.Debug.isDebuggable
            enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
            applicationIdSuffix = Build.Debug.applicationIdSuffix
            versionNameSuffix = Build.Debug.versionNameSuffix
        }

        create(BuildTypes.QA) {
            isMinifyEnabled = Build.Qa.isMinifyEnabled
            isDebuggable = Build.Qa.isDebuggable
            enableUnitTestCoverage = Build.Qa.enableUnitTestCoverage
            applicationIdSuffix = Build.Qa.applicationIdSuffix
            versionNameSuffix = Build.Qa.versionNameSuffix
        }
    }

    flavorDimensions.add(BuildDimensions.APP)
    flavorDimensions.add(BuildDimensions.STORE)

    productFlavors {
        BuildFlavor.Huawei.create(this)
        BuildFlavor.Google.create(this)
        BuildFlavor.Driver.create(this)
        BuildFlavor.Rider.create(this)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    loginModule()
    androidx()
    hilt()
    room()
    okHttp()
    retrofit()
    testImpl()
    androidTest()
    debugImpl()
    coreDataModule()
    coreDomainModule()
    corePresentationModule()
}
