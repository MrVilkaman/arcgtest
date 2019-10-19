package arch.module.skyeng.simpleTest.screens

import arch.module.skyeng.R
import arch.module.skyeng.ui.screenA.ScreenAFragment
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView


object ScreenAScreen : KScreen<ScreenAScreen>() {

    override val layoutId: Int? = R.layout.fragment_screena_layout
    override val viewClass: Class<*>? = ScreenAFragment::class.java

    val title = KTextView { withId(R.id.text) }

    val button1 = KButton { withId(R.id.button) }

    val button2 = KButton { withId(R.id.button2) }

    val button3 = KButton { withId(R.id.button3) }
}
