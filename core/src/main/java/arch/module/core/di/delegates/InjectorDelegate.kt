package arch.module.core.di.delegates

import android.content.Context

interface InjectorDelegate<T : Any> {
    fun diInject(context: Context, injectable: T)
}