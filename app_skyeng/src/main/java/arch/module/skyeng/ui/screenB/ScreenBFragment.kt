package arch.module.skyeng.ui.screenB;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import arch.module.skyeng.R
import arch.module.skyeng.ui.base.BaseFragment
import arch.module.skyeng.utils.ext.getOut
import arch.module.skyeng.utils.ext.putOut
import kotlinx.android.synthetic.main.fragment_screenb_layout.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppScreen


class ScreenB(private val out: ScreenBOut) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return ScreenBFragment().putOut(out)
    }
}

class ScreenBFragment(
) : BaseFragment<IScreenBView, ScreenBPresenter>(), IScreenBView {

    @InjectPresenter
    lateinit var presenter: ScreenBPresenter

    @ProvidePresenter
    fun providePresenter(): ScreenBPresenter = ScreenBPresenter(
        getOut()
    )

    override fun getLayoutId(): Int = R.layout.fragment_screenb_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button.setOnClickListener {
            presenter.onClickDone()
        }
    }
}