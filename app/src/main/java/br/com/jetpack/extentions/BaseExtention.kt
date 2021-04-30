package br.com.jetpack.extentions

import android.app.Application
import br.com.jetpack.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *Created by humbertokalex
 */

fun Application.startKoinApp() {
    startKoin {
        androidContext(this@startKoinApp)
        androidLogger()
        Modules.loadModules()
    }
}