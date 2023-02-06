package net.bitburst.pollpa

import android.app.Application
import net.bitburst.pollpa.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule)
        }
    }
}