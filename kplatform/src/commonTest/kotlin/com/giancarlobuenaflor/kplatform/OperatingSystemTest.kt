// TODO: who needs tests?

package com.giancarlobuenaflor.kplatform

import kotlin.test.Test
import kotlin.test.assertEquals

class FakePlatform(
    override val compilationTarget: CompilationTarget = CompilationTarget.JVM,
    override val operatingSystem: OperatingSystem =
        OperatingSystem.from(OperatingSystem.Family.IOS, "16.1"),
    override val environment: Map<String, String> = mapOf("MY_ENV" to "123")
) : Platform

class Test {

  val fakePlatform = FakePlatform()

  @Test
  fun test() {
    assertEquals(KPlatform().environment, mapOf("hello" to ""))
  }
}
//
// import kotlin.test.Test
// import kotlin.test.assertTrue
//
// class OperatingSystemTest {
//  private val platform = KPlatform()
//
//  @Test
//  fun `operatingSystemValues contains current operating system`() {
//    assertTrue(
//        Platform.operatingSystemValues.contains(platform.operatingSystem),
//        "Unknown OS: ${platform.operatingSystem}")
//  }
//
//  @Test
//  fun `operatingSystem correctly identifies the current operating system`() {
//    when (platform.operatingSystem) {
//      Platform.ANDROID -> assertTrue(platform.isAndroid)
//      Platform.IOS -> assertTrue(platform.isIOS)
//      Platform.MACOS -> assertTrue(platform.isMacOS)
//      Platform.TVOS -> assertTrue(platform.isTvOS)
//      Platform.WATCHOS -> assertTrue(platform.isWatchOS)
//      Platform.WINDOWS -> assertTrue(platform.isWindows)
//      Platform.LINUX -> assertTrue(platform.isLinux)
//    }
//  }
//
//  @Test
//  fun `operatingSystemVersion is not empty`() {
//    val version = platform.operatingSystemVersion
//    assertTrue(version.isNotEmpty(), "Operating system version is empty")
//  }
// }
