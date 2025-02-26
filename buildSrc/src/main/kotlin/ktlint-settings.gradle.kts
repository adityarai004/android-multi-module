import com.android.build.gradle.internal.tasks.factory.dependsOn

val ktlint: Configuration by configurations.creating


dependencies {
    ktlint("com.pinterest.ktlint:ktlint-cli:1.5.0"){
        attributes{
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

tasks.register<JavaExec>("ktlintFormat"){
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Runs ktlint to format the source code"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
    args = listOf(
        "-F",
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**"
    )
}

tasks{
    named("preBuild").dependsOn("ktlintFormat")
}