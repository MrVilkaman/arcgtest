package arch.module.skyeng.ui.screenA;

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import arch.module.skyeng.R
import arch.module.skyeng.ui.base.BaseFragment
import arch.module.skyeng.ui.base.BasePresenter
import kotlinx.android.synthetic.main.fragment_screena_layout.*
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ScreenA(private val out: ScreenAOut? = null) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return ScreenAFragment(out)
    }
}

class ScreenAFragment(
    private val out: ScreenAOut? = null
) : BaseFragment<IScreenAView, BasePresenter<IScreenAView>>(), IScreenAView {

    @InjectPresenter
    lateinit var presenter: ScreenAPresenter

    @ProvidePresenter
    fun providePresenter(): ScreenAPresenter = ScreenAPresenter(
        out!!
    )

    override fun getLayoutId(): Int = R.layout.fragment_screena_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button.setOnClickListener {
            presenter.openNextScreen()
        }
        button2.setOnClickListener {
            presenter.openChildScreen()
        }
        button3.setOnClickListener {
            presenter.openReplace()
        }
    }

    override fun showToast(counter: Int) {
        Toast.makeText(context!!, "Done ($counter)", Toast.LENGTH_SHORT).show()
    }

    override fun coordinatorDone() {
        Toast.makeText(context!!, "coordinatorDone", Toast.LENGTH_SHORT).show()
    }
}


@StateStrategyType(value = OneExecutionStateStrategy::class)
interface IScreenAView : MvpView {
    fun showToast(counter: Int)
    fun coordinatorDone()
}