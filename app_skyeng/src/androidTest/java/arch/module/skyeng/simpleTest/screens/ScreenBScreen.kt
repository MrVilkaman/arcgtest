package arch.module.skyeng.simpleTest.screens

import arch.module.skyeng.R
import arch.module.skyeng.ui.screenB.ScreenBFragment
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView


object ScreenBScreen : KScreen<ScreenBScreen>() {

    override val layoutId: Int? = R.layout.fragment_screenb_layout
    override val viewClass: Class<*>? = ScreenBFragment::class.java

    val title = KTextView { withId(R.id.title) }

    val button = KButton { withId(R.id.button) }
}
