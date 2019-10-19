package arch.module.skyeng.simpleTest.screens

import arch.module.skyeng.R
import arch.module.skyeng.ui.screenC.ScreenCFragment
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView


object ScreenCScreen : KScreen<ScreenCScreen>() {

    override val layoutId: Int? = R.layout.fragment_screenc_layout
    override val viewClass: Class<*>? = ScreenCFragment::class.java

    val title = KTextView { withId(R.id.title) }

    val button = KButton { withId(R.id.сontinue) }
}
