package arch.module.aword.ui.welcome;

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import arch.module.aword.R
import arch.module.aword.di.AwordAppComponent.Companion.appComponent
import arch.module.aword.di.delegates.WelcomeInjector
import arch.module.aword.ui.root.AwordActivity
import arch.module.core.di.PerScreen
import arch.module.core.ui.base.BaseFragment
import arch.module.core.utils.ext.withParam
import kotlinx.android.synthetic.main.fragment_welcome_layout.*
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@PerScreen
class WelcomeFragment : BaseFragment<WelcomePresenter>(WelcomeInjector), IWelcomeView {

    companion object {
        fun newInstance() = WelcomeFragment()
        fun hello() = WelcomeFragment().withParam {
            putString("KEY", "HELLO")
        }
    }

    @InjectPresenter
    override lateinit var presenter: WelcomePresenter

    @ProvidePresenter
    override fun providePresenter(): WelcomePresenter = super.providePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_welcome_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        go.setOnClickListener {
            presenter.showNextScreen()
        }

        arguments?.getString("KEY").let {
            text.text = it
        }


    }

    override fun showNotification() {
        createChannle()
        val manager =
            (context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

        val emptyIntent = Intent(context!!, AwordActivity::class.java)

        val pi = PendingIntent.getActivity(
            context!!, 100500,
            emptyIntent, PendingIntent.FLAG_ONE_SHOT
        )

        manager.notify(
            1, Notification.Builder(context!!, "q")
                .setContentTitle("Привет)")
                .setSmallIcon(arch.module.aword.R.mipmap.ic_launcher)
                .setContentIntent(pi)
                .build()
        )
    }

    private fun createChannle() {
        val name = "Канал"
        val description = "channel_description"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel("q", name, importance)
        mChannel.description = description
        val manager =
            (context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(mChannel)
    }
}

interface IWelcomeView : MvpView {
    @StateStrategyType(value = SkipStrategy::class)
    fun showNotification()
}