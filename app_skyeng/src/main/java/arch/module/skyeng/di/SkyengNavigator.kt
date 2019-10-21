package arch.module.skyeng.di

import androidx.fragment.app.FragmentActivity
import arch.module.skyeng.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class SkyengNavigator(
    val activity: FragmentActivity
) : SupportAppNavigator(activity, R.id.layout_child_fragment_container){
    protected override fun fragmentBack() {
        activity.supportFragmentManager.popBackStackImmediate()
    }
}