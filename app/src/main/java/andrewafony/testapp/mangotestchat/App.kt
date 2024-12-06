package andrewafony.testapp.mangotestchat

import andrewafony.testapp.home.di.homeModule
import andrewafony.testapp.profile.di.profileModule
import andrewafony.testapp.settings.di.settingsModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(profileModule, homeModule, settingsModule)
        }
    }
}