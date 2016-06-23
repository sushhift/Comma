package com.sushhift.comma

import android.app.Application


class App() : Application() {

    lateinit var graph: AppComponent

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun getAppComponent(): AppComponent {
        return graph
    }
}