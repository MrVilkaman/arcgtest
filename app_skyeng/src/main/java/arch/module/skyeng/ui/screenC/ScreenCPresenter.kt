package arch.module.skyeng.ui.screenC

import arch.module.skyeng.ui.base.BasePresenter
import moxy.InjectViewState
import moxy.MvpView
import javax.inject.Inject

typealias ScreenCOut = (ScreenCOutCmd) -> Unit

interface IScreenCView : MvpView

sealed class ScreenCOutCmd

object ContinuePressed : ScreenCOutCmd()


@InjectViewState
class ScreenCPresenter @Inject constructor(
    private val cmd: ScreenCOut
) : BasePresenter<IScreenCView>() {
    fun onClickContinue() {
        cmd(ContinuePressed)
    }
}

