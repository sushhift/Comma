package com.sushhift.comma

import dagger.Component
import javax.inject.Singleton


/**
 * Components are the injectors!
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun injectApp(application: App): App
}
