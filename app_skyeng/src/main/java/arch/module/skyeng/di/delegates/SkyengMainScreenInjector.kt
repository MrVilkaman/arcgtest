package arch.module.skyeng.di.delegates

import android.content.Context
import arch.module.core.di.findComponentDependencies
import arch.module.core.di.delegates.InjectorDelegate
import arch.module.skyeng.di.SkyengMainScreenComponent
import arch.module.skyeng.ui.mainscreen.SkyengMainScreenFragment

internal object SkyengMainScreenInjector :
    InjectorDelegate<SkyengMainScreenFragment> {
    override fun diInject(context: Context, injectable: SkyengMainScreenFragment) {
        SkyengMainScreenComponent.init(context, context.findComponentDependencies()).inject(injectable)
    }
}