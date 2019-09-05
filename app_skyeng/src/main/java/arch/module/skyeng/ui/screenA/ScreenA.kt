package arch.module.skyeng.ui.screenA;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import arch.module.skyeng.R
import arch.module.skyeng.ui.provideOut
import kotlinx.android.synthetic.main.fragment_screena_layout.*
import moxy.InjectViewState
import moxy.MvpAppCompatFragment
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType


class ScreenAFragment : MvpAppCompatFragment(), IScreenAView {

    companion object {
        fun newInstance() = ScreenAFragment()
    }

    @InjectPresenter
    lateinit var presenter: ScreenAPresenter

    @ProvidePresenter
    fun providePresenter(): ScreenAPresenter = ScreenAPresenter(
        context.provideOut()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    fun getLayoutId(): Int = R.layout.fragment_screena_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button.setOnClickListener {
            presenter.openNextScreen()
        }
        button2.setOnClickListener {
            presenter.openChildScreen()
        }
    }

    override fun showToast(counter: Int) {
        Toast.makeText(context!!, "Done ($counter)", Toast.LENGTH_SHORT).show()
    }

    override fun coordinatorDone() {
        Toast.makeText(context!!, "coordinatorDone", Toast.LENGTH_SHORT).show()
    }
}

sealed class ScreenAOutCmd

object GoPressed : ScreenAOutCmd()
object ChildPressed : ScreenAOutCmd()

class OnCreate(val input: ScreenAIn) : ScreenAOutCmd()


interface ScreenAIn {
    fun done(counter: Int)

    fun coordinatorDone()
}

@InjectViewState
class ScreenAPresenter constructor(
    private val out: (ScreenAOutCmd) -> Unit
) : MvpPresenter<IScreenAView>(), ScreenAIn {
    override fun onFirstViewAttach() {
        out(OnCreate(this))
    }

    override fun done(counter: Int) {
        viewState.showToast(counter)
    }

    fun openNextScreen() = out(GoPressed)

    fun openChildScreen() = out(ChildPressed)

    override fun coordinatorDone() {
        viewState.coordinatorDone()
    }
}

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface IScreenAView : MvpView {
    fun showToast(counter: Int)
    fun coordinatorDone()
}