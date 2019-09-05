package arch.module.auth.di.delegates

import android.content.Context
import arch.module.auth.di.SkyengAuthComponent
import arch.module.auth.ui.login.SkyengAuthScreenFragment
import arch.module.core.di.findComponentDependencies
import arch.module.core.di.delegates.InjectorDelegate

object SkyengAuthScreenInjector :
    InjectorDelegate<SkyengAuthScreenFragment> {
    override fun diInject(context: Context, injectable: SkyengAuthScreenFragment) {
        // прост передать context и внутри все достать
        SkyengAuthComponent.init(
            context.findComponentDependencies(),
            context.findComponentDependencies(),
            context.findComponentDependencies(),
            context.findComponentDependencies()
        ).inject(injectable)
    }
}