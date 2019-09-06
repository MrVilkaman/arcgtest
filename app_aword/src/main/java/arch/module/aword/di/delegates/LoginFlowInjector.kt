package arch.module.aword.di.delegates

import android.content.Context
import arch.module.auth.di.FlowLoginComponent
import arch.module.aword.ui.loginflow.LoginFlowFragment
import arch.module.core.di.delegates.InjectorDelegate

object LoginFlowInjector : InjectorDelegate<LoginFlowFragment> {
    override fun diInject(context: Context, injectable: LoginFlowFragment) {
        FlowLoginComponent.init(context).inject(injectable)
    }
}