package arch.module.core.utils.ext

import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.Fragment

//давайте всё-таки сохраним кэмелкейс в названии файла?

val EditText?.trimmedText: String
    get() {
        val text = this?.text ?: return ""
        return text.toString().trim { it <= ' ' }
    }

inline fun <F : Fragment> F.withParam(block: Bundle.() -> Unit): F {
    return this.apply {
        this.arguments = Bundle().apply {
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
