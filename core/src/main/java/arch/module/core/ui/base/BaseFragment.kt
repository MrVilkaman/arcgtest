package arch.module.core.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import arch.module.core.di.delegates.InjectorDelegate
import arch.module.core.other.NestedNavigation
import dagger.MembersInjector
import moxy.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider


abstract class BaseFragment<P : BasePresenter<*>>(injectorDelegate: InjectorDelegate<out Any>)
    : MvpAppCompatFragment(),
    NestedNavigation,
    InjectorDelegate<Any> by injectorDelegate as InjectorDelegate<Any> {

    abstract fun getLayoutId(): Int

    override fun onBackPressed(): Boolean = presenter.onBackPressed()

    abstract var presenter: P

    @Inject
    lateinit var presenterProvider: Provider<P>

    @CallSuper
    open fun providePresenter(): P = presenterProvider.get()

    override fun onAttach(activity: Context) {
        diInject(activity, this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    protected fun <D, T : CoreWidget<D>> attachWidget(moxyWidget: T, members: MembersInjector<T>) {
        members.injectMembers(moxyWidget)
        moxyWidget.init(mvpDelegate)
    }

    fun showToast(text: String) {
        context?.run {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun showToast(@StringRes text: Int) {
        showToast(getString(text))
    }
}