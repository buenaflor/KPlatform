import com.diffplug.spotless.LineEnding

plugins {
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  alias(libs.plugins.vanniktechMavenPublish) apply false
  alias(libs.plugins.dokka) apply false
  alias(libs.plugins.composeMultiplatform) apply false
  alias(libs.plugins.composeCompiler) apply false
  alias(libs.plugins.composeMultiplatformHotReload) apply false
  alias(libs.plugins.spotless)
}

spotless {
  lineEndings = LineEnding.UNIX

  kotlin {
    target("**/*.kt")
    ktfmt()
  }
  kotlinGradle {
    target("**/*.kts")
    ktfmt()
  }
}
