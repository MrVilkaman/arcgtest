package arch.module.auth.ui.login

import arch.module.auth.di.SkyengAuthComponent
import arch.module.auth.di.delegates.SkyengAuthScreenInjector
import arch.module.core.di.findComponentDependencies

// todo подумать как не делать такое говницо =(
// но если бы
class SkyengAuthScreenFragment() : AuthScreenFragment(SkyengAuthScreenInjector) {
}