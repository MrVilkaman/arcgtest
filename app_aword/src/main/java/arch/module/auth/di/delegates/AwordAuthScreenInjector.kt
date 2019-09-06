package arch.module.auth.di.delegates

import android.content.Context
import arch.module.auth.di.AwordAuthComponent
import arch.module.auth.ui.login.AwordAuthScreenFragment
import arch.module.core.di.delegates.InjectorDelegate

object AwordAuthScreenInjector : InjectorDelegate<AwordAuthScreenFragment> {
    override fun diInject(context: Context, injectable: AwordAuthScreenFragment) {
        AwordAuthComponent.init(context).inject(injectable)
    }
}