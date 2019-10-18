package arch.module.skyeng.coordinators

import android.util.Log
import arch.module.skyeng.ui.screenB.DonePressed
import arch.module.skyeng.ui.screenB.ScreenB
import arch.module.skyeng.ui.screenB.ScreenBOutCmd
import arch.module.skyeng.ui.screenC.ContinuePressed
import arch.module.skyeng.ui.screenC.ScreenC
import arch.module.skyeng.ui.screenC.ScreenCOutCmd
import ru.terrakok.cicerone.Router

sealed class ChildCoordinatorOutCmd

object ChildCoordinatorDone : ChildCoordinatorOutCmd()

interface FlowBCoordDependencies {
    val router: Router
}

class FlowBCoordinator : SerializableCoordinator<FlowBCoordDependencies>() {

    override val clazz: Class<FlowBCoordDependencies>
        get() = FlowBCoordDependencies::class.java


    @Transient
    private lateinit var router: Router

    override fun initDeps(deps: FlowBCoordDependencies) {
        Log.d("QWER", "$this RootCoordinator initDeps")
        router = deps.router
    }

    init {
        Log.d("QWER", "$this FlowBCoordinator init")
    }

    operator fun invoke(out: (ChildCoordinatorOutCmd) -> Unit) {
        router.navigateTo(ScreenC { outC: ScreenCOutCmd ->
            when (outC) {
                is ContinuePressed -> {
                    router.navigateTo(ScreenB { outB: ScreenBOutCmd ->
                        when (outB) {
                            is DonePressed -> out(ChildCoordinatorDone)
                        }
                    })
                }
            }
        })
    }
}
