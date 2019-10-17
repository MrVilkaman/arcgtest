package arch.module.skyeng.coordinators

import android.util.Log
import arch.module.skyeng.ui.screenA.*
import arch.module.skyeng.ui.screenB.DonePressed
import arch.module.skyeng.ui.screenB.ScreenB
import arch.module.skyeng.ui.screenB.ScreenBOutCmd
import ru.terrakok.cicerone.Router
import java.lang.ref.WeakReference


class RootCoordinator(
    private val router: Router
) {
    init {
        Log.d("QWER", "$this RootCoordinator init")
    }

    private var counter: Int = 0

    private lateinit var screenAIn: WeakReference<ScreenAIn>

    fun showStartScreen() {
        router.navigateTo(ScreenA { outA: ScreenAOutCmd ->
            when (outA) {
                is GoPressed -> router.navigateTo(ScreenB { outB: ScreenBOutCmd ->
                    when (outB) {
                        is DonePressed -> {
                            router.exit()
                            screenAIn.get()?.done(++counter)
                        }
                    }
                })
                is ChildPressed -> FlowBCoordinator(router)() {
                    when (it) {
                        is ChildCoordinatorDone -> {
                            screenAIn.get()?.coordinatorDone()
                            router.backTo(ScreenA())
                        }
                    }
                }
                is OnCreate -> screenAIn = WeakReference(outA.input)
            }
        })
    }
}
