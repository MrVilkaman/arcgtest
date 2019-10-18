package arch.module.skyeng.ui.screenC;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import arch.module.skyeng.R
import arch.module.skyeng.ui.base.BaseFragment
import arch.module.skyeng.utils.ext.getOut
import arch.module.skyeng.utils.ext.putOut
import kotlinx.android.synthetic.main.fragment_screenc_layout.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ScreenC(
    private val out: ScreenCOut
) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return ScreenCFragment().putOut(out)
    }
}

class ScreenCFragment(
) : BaseFragment<IScreenCView, ScreenCPresenter>(), IScreenCView {

    @InjectPresenter
    lateinit var presenter: ScreenCPresenter

    @ProvidePresenter
    fun providePresenter(): ScreenCPresenter = ScreenCPresenter(
        getOut()
    )

    override fun getLayoutId(): Int = R.layout.fragment_screenc_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        сontinue.setOnClickListener {
            presenter.onClickContinue()
        }
    }
}