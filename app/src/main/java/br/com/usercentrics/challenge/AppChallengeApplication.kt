package br.com.usercentrics.challenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class AppChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin(){
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@AppChallengeApplication)

        }
    }
}