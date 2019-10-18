@file:Suppress("LeakingThis")

package arch.module.skyeng.coordinators

import android.util.Log
import arch.module.skyeng.di.CoordinatorDependenciesStore
import java.io.IOException
import java.io.Serializable


abstract class SerializableCoordinator<CoordDeps : Any> : Serializable {

    protected abstract val clazz: Class<CoordDeps>

    init {
        doMagic()
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(stream: java.io.ObjectInputStream) {
        Log.d("QWER", "$this  readObject")
        doMagic()
        stream.defaultReadObject()
    }

    private fun doMagic() {
        initDeps(CoordinatorDependenciesStore.getFor(clazz))
    }

    abstract fun initDeps(deps: CoordDeps)
}
