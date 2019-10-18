@file:Suppress("LeakingThis")

package arch.module.skyeng.coordinators

import android.util.Log
import java.io.IOException
import java.io.Serializable


abstract class SerializableCoordinator : Serializable {

    init {
        initDeps()
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(stream: java.io.ObjectInputStream) {
        Log.d("QWER", "$this  readObject")
        initDeps()
        stream.defaultReadObject()
    }

    abstract fun initDeps()
}