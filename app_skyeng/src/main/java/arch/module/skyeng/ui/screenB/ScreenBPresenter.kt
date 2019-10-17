package arch.module.skyeng.ui.screenB

import arch.module.skyeng.ui.base.BasePresenter
import moxy.InjectViewState
import moxy.MvpView
import javax.inject.Inject

typealias ScreenBOut = (ScreenBOutCmd) -> Unit

interface IScreenBView : MvpView

sealed class ScreenBOutCmd

object DonePressed : ScreenBOutCmd()

@InjectViewState
class ScreenBPresenter @Inject constructor(
    private val cmd: ScreenBOut
) : BasePresenter<IScreenBView>() {
    fun onClickDone() {
        cmd(DonePressed)
    }

}