package arch.module.core.ui.base

import arch.module.core.R
import arch.module.core.di.delegates.InjectorDelegate


abstract class FlowFragment<P : BasePresenter<*>>(
    val injector: InjectorDelegate<out Any>
) : BaseFragment<P>(injector) {

    override fun getLayoutId(): Int = R.layout.fragment_single_container

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.layout_child_fragment_container) as? BaseFragment<*>

    override fun onBackPressed(): Boolean {
        return currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

}