sealed class Build {
    open val isMinifyEnabled: Boolean = false
    open val enableUnitTestCoverage: Boolean = false
    open val isDebuggable = false
    open val applicationIdSuffix = ""
    open val versionNameSuffix = ""

    object Debug: Build(){
        override val versionNameSuffix = "-debug"
        override val applicationIdSuffix = ".debug"
        override val isDebuggable = true
        override val isMinifyEnabled = true
        override val enableUnitTestCoverage = true
    }

    object Qa: Build(){
        override val versionNameSuffix = "-qa"
        override val applicationIdSuffix = ".qa"
        override val isDebuggable = false
        override val isMinifyEnabled = false
        override val enableUnitTestCoverage = true
    }

    object Release: Build(){
        override val isDebuggable = false
        override val isMinifyEnabled = true
        override val enableUnitTestCoverage = false
    }

}