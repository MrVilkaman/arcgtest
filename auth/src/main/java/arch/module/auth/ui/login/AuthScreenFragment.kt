package arch.module.auth.ui.login

import android.os.Bundle
import android.view.View
import arch.module.auth.R
import arch.module.core.ui.base.BaseFragment
import arch.module.core.di.delegates.InjectorDelegate
import arch.module.core.utils.ext.trimmedText
import kotlinx.android.synthetic.main.fragment_auth_screen.*
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType


abstract class AuthScreenFragment(
    injector: InjectorDelegate<out Any>
) : BaseFragment<AuthScreenPresenter>(injector),
    AuthScreenView {

    @InjectPresenter
    override lateinit var presenter: AuthScreenPresenter

    @ProvidePresenter
    override fun providePresenter(): AuthScreenPresenter = super.providePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_auth_screen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        enter_login.setOnClickListener {
            presenter.doLogin(login.trimmedText, password.trimmedText)
        }
    }

    override fun showError(it: Throwable?) {
        it?.let { showToast(it.toString()) }
    }
}

interface AuthScreenView : MvpView {
    @StateStrategyType(value = SkipStrategy::class)
    fun showError(it: Throwable?)
}