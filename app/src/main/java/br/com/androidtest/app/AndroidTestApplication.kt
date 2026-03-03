package br.com.androidtest.app

import android.app.Application
import br.com.androidtest.common.AppConfig
import br.com.androidtest.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AndroidTestApplication)
            properties(mapOf(AppConfig.PRIVACY_URL_PROPERTY_KEY to AppConfig.DEFAULT_PRIVACY_URL))
            modules(appModules)
        }
    }
}
