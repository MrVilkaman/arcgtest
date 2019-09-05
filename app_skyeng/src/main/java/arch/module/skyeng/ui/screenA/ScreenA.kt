package arch.module.skyeng.ui.screenA;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import arch.module.skyeng.R
import arch.module.skyeng.coordinators.CoordinatorParamHolder
import kotlinx.android.synthetic.main.fragment_screena_layout.*
import moxy.InjectViewState
import moxy.MvpAppCompatFragment
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ScreenAFragment : MvpAppCompatFragment(), IScreenAView {

    companion object {
        fun newInstance() = ScreenAFragment()
    }

    @InjectPresenter
    lateinit var presenter: ScreenAPresenter

    @ProvidePresenter
    fun providePresenter(): ScreenAPresenter = ScreenAPresenter(
        CoordinatorParamHolder.provideOut("key") as (ScreenAOutCmd) -> Unit
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
    }

    override fun showToast() {
        Toast.makeText(context!!, "Done", Toast.LENGTH_SHORT).show()
    }
}

sealed class ScreenAOutCmd

object GoPressed : ScreenAOutCmd()

class OnCreate(val input: ScreenAIn) : ScreenAOutCmd()


interface ScreenAIn {
    fun done()
}

@InjectViewState
class ScreenAPresenter constructor(
    private val out: (ScreenAOutCmd) -> Unit
) : MvpPresenter<IScreenAView>(), ScreenAIn {
    override fun onFirstViewAttach() {
        out(OnCreate(this))
    }

    override fun done() {
        viewState.showToast()
    }

    fun openNextScreen() {
        out(GoPressed)
    }

}

interface IScreenAView : MvpView {
    fun showToast()
}