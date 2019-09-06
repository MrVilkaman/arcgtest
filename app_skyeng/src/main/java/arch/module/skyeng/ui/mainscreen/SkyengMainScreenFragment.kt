package arch.module.skyeng.ui.mainscreen

import android.os.Bundle
import android.view.View
import arch.module.core.ui.base.BaseFragment
import arch.module.skyeng.R
import arch.module.skyeng.di.delegates.SkyengMainScreenInjector
import arch.module.userprofile.ui.UserProfileInformerWidget
import dagger.MembersInjector
import kotlinx.android.synthetic.main.fragment_skyeng_main_screen.*
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


internal class SkyengMainScreenFragment : BaseFragment<SkyengMainScreenPresenter>(
    SkyengMainScreenInjector
),
    ISkyengMainScreenView {

    @InjectPresenter
    override lateinit var presenter: SkyengMainScreenPresenter

    @ProvidePresenter
    override fun providePresenter(): SkyengMainScreenPresenter = super.providePresenter()

    override fun getLayoutId(): Int = R.layout.fragment_skyeng_main_screen

    @Inject
    lateinit var userProfileInformerWidgetMembersInjector: MembersInjector<UserProfileInformerWidget>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout.setOnClickListener {
            presenter.logout()
        }
        attachWidget(userProfileInformerWidget, userProfileInformerWidgetMembersInjector)
    }
}

internal interface ISkyengMainScreenView : MvpView