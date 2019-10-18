package arch.module.skyeng.coordinators

import android.util.Log
import arch.module.skyeng.di.Navigation
import arch.module.skyeng.ui.screenA.*
import arch.module.skyeng.ui.screenB.DonePressed
import arch.module.skyeng.ui.screenB.ScreenB
import arch.module.skyeng.ui.screenB.ScreenBOutCmd
import ru.terrakok.cicerone.Router
import java.lang.ref.WeakReference


class RootCoordinator : SerializableCoordinator() {
    init {
        Log.d("QWER", "$this RootCoordinator init")
    }


    @Transient
    private lateinit var router: Router

    //todo не сериализуется WeakReference и после восстановления ссылка теряется =(
    @Transient
    private var screenAIn: WeakReference<ScreenAIn> = WeakReference<ScreenAIn>(null)

    override fun initDeps() {
        Log.d("QWER", "$this RootCoordinator initDeps")
        //todo подумать как прокидывать зависимости в координаторы?
        router = Navigation.instance.router
    }

    private var counter: Int = 0

    fun showStartScreen() {
        router.navigateTo(ScreenA { outA: ScreenAOutCmd ->
            when (outA) {
                is GoPressed -> router.navigateTo(ScreenB { outB: ScreenBOutCmd ->
                    when (outB) {
                        is DonePressed -> {
                            screenAIn.get()?.done(++counter)
                            router.exit()
                        }
                    }
                })
                is ChildPressed -> FlowBCoordinator()() {
                    when (it) {
                        is ChildCoordinatorDone -> {
                            screenAIn.get()?.coordinatorDone()
                            router.backTo(ScreenA())
                        }
                    }
                }
                is ReplacePressed -> router.replaceScreen(ScreenB { outB: ScreenBOutCmd ->
                    when (outB) {
                        is DonePressed -> {
                            router.exit()
                        }
                    }
                })

                is OnCreate -> screenAIn = WeakReference(outA.input)
            }
        })
    }
}
