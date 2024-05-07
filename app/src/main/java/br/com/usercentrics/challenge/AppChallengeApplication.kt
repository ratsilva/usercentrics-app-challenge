package br.com.usercentrics.challenge

import android.app.Application
import br.com.usercentrics.feature_virtual_cost.featureVirtualCostModules
import br.com.usercentrics.plugin_data_privacy.DataPrivacyPlugin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class AppChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@AppChallengeApplication)

            modules(module {
                single { DataPrivacyPlugin.initialize(this@AppChallengeApplication) }
            })
            modules(appModule)
            modules(featureVirtualCostModules)

        }
    }
}