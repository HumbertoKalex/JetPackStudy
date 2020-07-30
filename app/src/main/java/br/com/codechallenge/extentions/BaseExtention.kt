package br.com.codechallenge.extentions

import android.app.Application
import br.com.codechallenge.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.startKoinApp() {
    startKoin {
        androidContext(this@startKoinApp)
        androidLogger()
        Modules.loadModules()
    }
}