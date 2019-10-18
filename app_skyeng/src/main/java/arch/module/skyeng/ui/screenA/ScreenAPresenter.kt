package arch.module.skyeng.ui.screenA

import arch.module.skyeng.ui.base.BasePresenter
import moxy.InjectViewState

typealias ScreenAOut = (ScreenAOutCmd) -> Unit

sealed class ScreenAOutCmd

object GoPressed : ScreenAOutCmd()
object ChildPressed : ScreenAOutCmd()

object ReplacePressed : ScreenAOutCmd()

class OnCreate(val input: ScreenAIn) : ScreenAOutCmd()


interface ScreenAIn {
    fun done(counter: Int)

    fun coordinatorDone()
}


@InjectViewState
class ScreenAPresenter constructor(
    private val out: ScreenAOut
) : BasePresenter<IScreenAView>(),
    ScreenAIn {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        out(OnCreate(this))
    }

    override fun done(counter: Int) {
        viewState.showToast(counter)
    }

    fun openNextScreen() = out(GoPressed)

    fun openChildScreen() = out(ChildPressed)

    fun openReplace() = out(ReplacePressed)

    override fun coordinatorDone() {
        viewState.coordinatorDone()
    }
}