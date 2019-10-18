package arch.module.skyeng.utils.ext

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable


inline fun <F : Fragment> F.withParam(block: Bundle.() -> Unit): F {
    return this.apply {
        val bundle = this.arguments ?: Bundle()
        this.arguments = bundle.apply {
            block.invoke(this)
        }
    }
}

fun Fragment.getBooleanParam(key: String): Boolean {
    return arguments?.getBoolean(key) ?: throw IllegalStateException()
}

fun Fragment.getIntParam(key: String): Int {
    return arguments?.getInt(key) ?: throw IllegalStateException()
}

fun Fragment.getStringParam(key: String): String {
    return arguments?.getString(key) ?: throw IllegalStateException()
}


fun <T> Fragment.getOut(): T {
    return arguments?.getSerializable("out") as T ?: throw IllegalStateException()
}

fun <T> Fragment.putOut(out: (T) -> Unit): Fragment {
    return withParam {
        putSerializable("out", out as Serializable)
    }
}
