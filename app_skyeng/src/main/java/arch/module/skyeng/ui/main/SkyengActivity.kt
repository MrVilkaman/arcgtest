package arch.module.skyeng.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import arch.module.skyeng.R
import arch.module.skyeng.coordinators.RootCoordinator
import arch.module.skyeng.di.Navigation
import arch.module.skyeng.di.SkyengNavigator


class SkyengActivity : AppCompatActivity() {

    private val navigatorHolder = Navigation.instance.navigatorHolder


    override fun onCreate(savedInstanceState: Bundle?) {
        val wasZhepka: Boolean = false
//        val wasZhepka: Boolean = try {
        super.onCreate(savedInstanceState)
//            false
//        } catch (e: ZhepkaException) {
//            // мимикрия под iOS (андрей сказал норм)
//            // ошибка потому что фрагменты восстанавливаются, а лямбды - нет
//            true
//        }
        setContentView(R.layout.layout_skyeng)

        if (savedInstanceState == null || wasZhepka) {
            val rootCoordinator = RootCoordinator()
            Toast.makeText(this, "cold start", Toast.LENGTH_SHORT).show()
            rootCoordinator.showStartScreen()
        } else {
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private val navigator by lazy { SkyengNavigator(this) }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.layout_child_fragment_container)
//
//        val isHandleByFragment = fragment is NestedNavigation && fragment.onBackPressed()
//        if (!isHandleByFragment) {
        super.onBackPressed()
//        }
    }
}