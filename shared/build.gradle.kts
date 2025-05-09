import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  `maven-publish`
}

kotlin {
  explicitApi()

  androidTarget {
    compilations.all {
      compileTaskProvider.configure { compilerOptions { jvmTarget.set(JvmTarget.JVM_1_8) } }
    }
  }

  jvm {
    compilations.all {
      compileTaskProvider.configure { compilerOptions { jvmTarget.set(JvmTarget.JVM_1_8) } }
    }
  }

  js {
    browser()
    nodejs()
  }

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    browser()
    nodejs()
  }

  listOf(
          iosX64(),
          iosArm64(),
          iosSimulatorArm64(),
          macosArm64(),
          macosX64(),
          tvosX64(),
          tvosArm64(),
          tvosSimulatorArm64(),
          watchosX64(),
          watchosArm64(),
          watchosSimulatorArm64(),
      )
      .forEach {
        it.binaries.framework {
          baseName = "shared"
          isStatic = true
        }
      }

  sourceSets { commonTest.dependencies { implementation(libs.kotlin.test) } }
}

android {
  namespace = "com.giancarlobuenaflor.kplatform"
  compileSdk = 35
  defaultConfig { minSdk = 24 }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}
