package arch.module.skyeng.coordinators

import android.util.Log
import arch.module.skyeng.ui.screenA.*
import arch.module.skyeng.ui.screenB.DonePressed
import arch.module.skyeng.ui.screenB.ScreenB
import arch.module.skyeng.ui.screenB.ScreenBOutCmd
import ru.terrakok.cicerone.Router
import java.io.IOException
import java.lang.ref.WeakReference

interface RootCoordDependencies {
    val router: Router
}

class RootCoordinator : SerializableCoordinator<RootCoordDependencies>() {
    override val clazz: Class<RootCoordDependencies>
        get() = RootCoordDependencies::class.java

    companion object {
        var instance: RootCoordinator? = null
    }

    init {
        doMagic()
        instance = this
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(stream: java.io.ObjectInputStream) {
        if (instance == null) {
            instance = this

            doMagic()
            stream.defaultReadObject()
        }
    }


    private fun readResolve(): Any? {
        return if (instance != null) instance
        else this
    }

    @Transient
    private lateinit var router: Router

    //todo не сериализуется WeakReference и после восстановления ссылка теряется =(
    @Transient
    private lateinit var screenAIn: WeakReference<ScreenAIn>

    override fun initDeps(deps: RootCoordDependencies) {
        screenAIn =
            WeakReference<ScreenAIn>(null) // в поле инициализация не катит при восстановлении

        router = deps.router
        Log.d("QWER", "$this RootCoordinator initDeps $router")
    }

    private var counter: Int = 0

    fun showStartScreen() {
        router.navigateTo(ScreenA { outA: ScreenAOutCmd ->
            when (outA) {
                is GoPressed -> router.navigateTo(ScreenB { outB: ScreenBOutCmd ->
                    when (outB) {
                        is DonePressed -> {
                            router.exit()
                            screenAIn.get()?.done(++counter)
                            Log.d("QWER", "DONE PRESSED ${screenAIn.get()} ${this}")
                        }
                    }
                })
                is ChildPressed -> FlowBCoordinator()() {
                    when (it) {
                        is ChildCoordinatorDone -> {
                            router.backTo(ScreenA())
                            screenAIn.get()?.coordinatorDone()
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

                is OnCreate -> {
                    Log.d("QWER", "ON CREATE A ${this}")
                    screenAIn = WeakReference(outA.input)
                }
            }
        })
    }
}
