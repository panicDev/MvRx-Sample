package id.paniclabs.android.mvrxplayground.presentation

import android.app.Application
import id.paniclabs.android.mvrxplayground.presentation.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(AppModule.retrofit, AppModule.repositories, AppModule.useCases)
        }
    }
}