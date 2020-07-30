package br.com.codechallenge.app

import android.app.Application
import br.com.codechallenge.extentions.startKoinApp

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinApp()
    }
}