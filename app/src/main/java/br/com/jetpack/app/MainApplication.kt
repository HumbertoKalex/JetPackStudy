package br.com.jetpack.app

import android.app.Application
import br.com.jetpack.extentions.startKoinApp

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinApp()
    }
}