package br.com.usercentrics.plugin_data_privacy

import android.content.Context
import androidx.annotation.VisibleForTesting
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyService
import br.com.usercentrics.plugin_data_privacy.service.DataPrivacyServiceFactory
import br.com.usercentrics.plugin_data_privacy.service.DefaultDataPrivacyServiceFactory

class DataPrivacyPlugin {
    companion object {

        @VisibleForTesting
        var service: DataPrivacyService? = null

        @VisibleForTesting
        var serviceFactory: DataPrivacyServiceFactory = DefaultDataPrivacyServiceFactory()

        fun initialize(context: Context): DataPrivacyService {
            return service ?: synchronized(this) {
                service = serviceFactory.create().also {
                    it.initialize(context)
                }
                return service!!
            }
        }

    }

}