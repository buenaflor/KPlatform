import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.vanniktechMavenPublish)
  alias(libs.plugins.dokka)
}

group = project.property("group") as String

version = project.property("version") as String

kotlin {
  explicitApi()

  androidTarget {
    publishAllLibraryVariants()
    compilations.all {
      compileTaskProvider.configure { compilerOptions { jvmTarget.set(JvmTarget.JVM_1_8) } }
    }
  }

  jvm {
    compilations.all {
      compileTaskProvider.configure { compilerOptions { jvmTarget.set(JvmTarget.JVM_1_8) } }
    }
  }

  js { browser() }

  @OptIn(ExperimentalWasmDsl::class) wasmJs { browser() }

  iosX64()
  iosArm64()
  iosSimulatorArm64()
  macosArm64()
  macosX64()
  tvosX64()
  tvosArm64()
  tvosSimulatorArm64()
  watchosX64()
  watchosArm64()
  watchosSimulatorArm64()

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
  buildFeatures {
    buildConfig = true
  }
}

mavenPublishing {
  publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

  signAllPublications()

  coordinates(group.toString(), "kplatform", version.toString())

  pom {
    name = "KPlatform"
    description =
        "A lightweight pluggable Kotlin Multiplatform library that lets you use runtime information of the system."
    inceptionYear = "2025"
    url = "https://github.com/buenaflor/kplatform"
    licenses {
      license {
        name = "The Apache License, Version 2.0"
        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
        distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
      }
    }
    developers {
      developer {
        id = "buenaflor"
        name = "Giancarlo Buenaflor"
        url = "https://www.giancarlobuenaflor.com/"
        email = "giancarlobuenaflor97@gmail.com"
      }
    }
    scm { url = "https://github.com/buenaflor/kplatform" }
  }
}
