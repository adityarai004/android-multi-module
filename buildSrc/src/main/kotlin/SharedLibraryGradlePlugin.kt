import Build.Release.isDebuggable
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class SharedLibraryGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addConfigurations()
        project.addAndroidConfigurations()
        project.addKotlinOptions()
    }

    private fun Project.addConfigurations(){
        plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(BuildPlugins.KAPT)
        plugins.apply(BuildPlugins.KTLINT)
    }

    private fun Project.addAndroidConfigurations(){
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK
            defaultConfig{
                minSdk = BuildConfig.MIN_SDK
                testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
            }

            compileOptions{
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }


            flavorDimensions.add(BuildDimensions.APP)
            flavorDimensions.add(BuildDimensions.STORE)

            productFlavors{
                BuildFlavor.Huawei.createLibrary(this)
                BuildFlavor.Google.createLibrary(this)
                BuildFlavor.Driver.createLibrary(this)
                BuildFlavor.Rider.createLibrary(this)
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }


            buildTypes {
                getByName(BuildTypes.RELEASE){
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    isMinifyEnabled = Build.Release.isMinifyEnabled
                    enableUnitTestCoverage = Build.Release.enableUnitTestCoverage
                }

                getByName(BuildTypes.DEBUG){
                    isMinifyEnabled = Build.Debug.isMinifyEnabled
                    enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
                }

                create(BuildTypes.QA){
                    isMinifyEnabled = Build.Qa.isMinifyEnabled
                    enableUnitTestCoverage = Build.Qa.enableUnitTestCoverage
                }

            }

        }
    }

    private fun Project.addKotlinOptions(){
        tasks.withType<KotlinCompile>{
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
}