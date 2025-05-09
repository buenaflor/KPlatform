import com.diffplug.spotless.LineEnding

plugins {
  alias(libs.plugins.androidLibrary).apply(false)
  alias(libs.plugins.kotlinMultiplatform).apply(false)
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
