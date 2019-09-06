package arch.module.aword.di.delegates

import android.content.Context
import arch.module.aword.di.AwordAppComponent
import arch.module.aword.ui.welcome.WelcomeFragment
import arch.module.core.di.delegates.InjectorDelegate

object WelcomeInjector : InjectorDelegate<WelcomeFragment> {
    override fun diInject(context: Context, injectable: WelcomeFragment) {
        AwordAppComponent.appComponent.welcomeComponentBuilder()
            .build().inject(injectable)
    }
}