package arch.module.aword.ui.loginflow;

import android.os.Bundle
import android.view.View
import arch.module.auth.di.FlowLoginComponent
import arch.module.aword.R
import arch.module.aword.di.delegates.LoginFlowInjector
import arch.module.core.di.PerScreen
import arch.module.core.di.modules.FlowNavigatorHolder
import arch.module.core.di.modules.FlowRouter
import arch.module.core.other.MvpRouter
import arch.module.core.ui.base.FlowFragment
import kotlinx.android.synthetic.main.fragment_loginflow_layout.*
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject


@PerScreen
class LoginFlowFragment : FlowFragment<LoginFlowPresenter>(LoginFlowInjector), ILoginFlowView {


    companion object {
        fun newInstance() = LoginFlowFragment()
    }

    @InjectPresenter
    override lateinit var presenter: LoginFlowPresenter

    @ProvidePresenter
    override fun providePresenter(): LoginFlowPresenter = super.providePresenter()


    @Inject
    lateinit var router: FlowRouter

    @Inject
    lateinit var globalRouter: MvpRouter

    @Inject
    @field:FlowNavigatorHolder
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by lazy { LoginFlowNavigator(globalRouter, childFragmentManager, this) }

    override fun getLayoutId(): Int = R.layout.fragment_loginflow_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            //халтура)
            router.newRootScreen(LoginFlowRoutingScreen.AUTH)
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun updateText(text: String) {
        title.text = text
    }
}


interface ILoginFlowView : MvpView {
    fun updateText(text: String)
}