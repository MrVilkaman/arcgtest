package arch.module.core.di.delegates

import android.content.Context

object EmptyInjector : InjectorDelegate<Any> {
    override fun diInject(context: Context, injectable: Any) {}
}