package arch.module.skyeng.di

import arch.module.skyeng.coordinators.FlowBCoordDependencies
import arch.module.skyeng.coordinators.RootCoordDependencies
import arch.module.skyeng.utils.ext.cast
import ru.terrakok.cicerone.Router


object CoordinatorDependenciesStore {


    fun <T : Any> getFor(clazz: Class<T>): T {
        return when (clazz) {
            RootCoordDependencies::class.java -> createRootCoordDependencies().cast()
            FlowBCoordDependencies::class.java -> createFlowBCoordDependencies().cast()
            else -> throw IllegalArgumentException("not found: ${clazz}")
        }
    }

    private fun createRootCoordDependencies(): RootCoordDependencies {
        return object : RootCoordDependencies {
            override val router: Router
                get() = Navigation.instance.router

        }
    }

    private fun createFlowBCoordDependencies(): FlowBCoordDependencies {
        return object : FlowBCoordDependencies {
            override val router: Router
                get() = Navigation.instance.router

        }
    }
}