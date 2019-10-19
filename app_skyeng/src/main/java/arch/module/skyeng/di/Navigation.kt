package arch.module.skyeng.di

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


class Navigation private constructor() {

    companion object {
        val instance = Navigation()
    }


    val router = Router()
    val navigatorHolder: NavigatorHolder = Cicerone.create(router).navigatorHolder

}