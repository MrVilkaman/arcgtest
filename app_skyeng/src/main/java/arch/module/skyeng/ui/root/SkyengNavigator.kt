package arch.module.skyeng.ui.root

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import arch.module.auth.ui.login.SkyengAuthScreenFragment
import arch.module.skyeng.R
import arch.module.skyeng.ui.SkyengRoutingScreen
import arch.module.skyeng.ui.mainscreen.SkyengMainScreenFragment
import ru.terrakok.cicerone.android.SupportAppNavigator


class SkyengNavigator(
    activity: FragmentActivity
) : SupportAppNavigator(activity, R.id.layout_child_fragment_container) {

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        return null
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
        SkyengRoutingScreen.AUTH -> SkyengAuthScreenFragment()
        SkyengRoutingScreen.MAIN -> SkyengMainScreenFragment()
        else -> null
    }
}