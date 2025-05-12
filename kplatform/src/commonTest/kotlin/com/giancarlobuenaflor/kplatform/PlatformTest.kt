import com.giancarlobuenaflor.kplatform.KPlatform
import com.giancarlobuenaflor.kplatform.OperatingSystem
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertTrue

class PlatformTest {
  private lateinit var platform: KPlatform

  @BeforeTest
  fun setUp() {
    platform = KPlatform()
  }

  @Test
  fun `isDebug returns true by default in tests`() {
    assertTrue(platform.isDebug)
  }

  @Test
  fun `operatingSystem returns correct values`() {
    // TODO: these are primitive tests
    val operatingSystem = platform.operatingSystem
    val environment = platform.environment
    val osName = environment["OS_NAME"]
    // This is the workflow config target, not the compilation target
    val targetName = environment["TARGET_NAME"]

    val simulatorDeviceName = environment["SIMULATOR_DEVICE_NAME"]
    if (simulatorDeviceName != null) {
      if (simulatorDeviceName.contains("iPhone")) {
        assertIs<OperatingSystem.Ios>(operatingSystem)
      }
      if (simulatorDeviceName.contains("Apple TV")) {
        assertIs<OperatingSystem.TvOs>(operatingSystem)
      }
      if (simulatorDeviceName.contains("Apple Watch")) {
        assertIs<OperatingSystem.WatchOs>(operatingSystem)
      }
    }

    if (osName == null || targetName == null) {
      return
    }

    if (targetName == "android") {
      assertIs<OperatingSystem.Android>(operatingSystem)
    } else {
      if (osName.contains("ubuntu")) {
        assertIs<OperatingSystem.Linux>(operatingSystem)
      }
      if (osName.contains("macos")) {
        assertIs<OperatingSystem.MacOs>(operatingSystem)
      }
    }
  }

  @Test
  fun `compilationTarget returns correct values`() {
    // TODO: these are primitive tests
    val environment = platform.environment
    val osName = environment["OS_NAME"]
    // This is the workflow config target, not the compilation target
    val targetName = environment["TARGET_NAME"]

    val simulatorDeviceName = environment["SIMULATOR_DEVICE_NAME"]
    if (simulatorDeviceName != null) {
      if (simulatorDeviceName.contains("iPhone")) {
        assertTrue(platform.compilationTarget.isIOS)
        assertTrue(platform.compilationTarget.isMobile)
      }
      if (simulatorDeviceName.contains("Apple TV")) {
        assertTrue(platform.compilationTarget.isTvOS)
      }
      if (simulatorDeviceName.contains("Apple Watch")) {
        assertTrue(platform.compilationTarget.isWatchOS)
      }
    }

    if (osName == null || targetName == null) {
      return
    }

    if (targetName == "android") {
      assertTrue(platform.compilationTarget.isAndroid)
      assertTrue(platform.compilationTarget.isMobile)
    }
  }
}
