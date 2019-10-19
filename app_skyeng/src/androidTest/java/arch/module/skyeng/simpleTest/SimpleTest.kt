package arch.module.skyeng.simpleTest


import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import arch.module.skyeng.simpleTest.screens.ScreenAScreen
import arch.module.skyeng.simpleTest.screens.ScreenBScreen
import arch.module.skyeng.simpleTest.screens.ScreenCScreen
import arch.module.skyeng.ui.main.SkyengActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * In this example you can observe a test tuned by default Kaspresso configuration.
 * When you start the test you can see output of default Kaspresso interceptors:
 * - a lot of useful logs
 * - failure handling
 * - screenshots in the device
 * * Also you can observe the test dsl simplifying a writing of any test
 */
@RunWith(AndroidJUnit4::class)
class SimpleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )


    @get:Rule
    val activityTestRule = ActivityTestRule(SkyengActivity::class.java, true, false)

    @Test
    fun test() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
            activityTestRule.finishActivity()
        }.run {

            step("Открыть приложение") {
                testLogger.i("I am testLogger")
                ScreenAScreen {
                    title {
                        isVisible()
                        hasText("ЭКРАН A")
                    }
                }
            }

            step("Открыть следующий экран") {
                ScreenAScreen {
                    button2 {
                        click()
                    }
                }

                ScreenCScreen {
                    title {
                        isVisible()
                        hasText("ЭКРАН C")
                    }
                    button {
                        hasText("Continue")
                        click()
                    }
                }
            }


            step("Открыть следующий экран") {
                ScreenBScreen {
                    title {
                        isVisible()
                        hasText("ЭКРАН B")
                    }
                    button {
                        click()
                    }
                }

                ScreenAScreen {
                    title {
                        isVisible()
                        hasText("ЭКРАН A")
                    }
                }

            }
        }
    }

    @Test
    fun test_flow() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
            activityTestRule.finishActivity()
        }.run {

            step("Открыть приложение") {
                ScreenAScreen {
                    title {
                        isVisible()
                        hasText("ЭКРАН A")
                    }
                    button1 {
                        click()
                    }
                }
            }

            step("Вернуться назад") {
                device.exploit.pressBack(true)
            }
        }
    }
}