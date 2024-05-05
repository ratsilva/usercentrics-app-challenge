package br.com.usercentrics.plugin_data_privacy.service

import android.content.Context
import br.com.usercentrics.plugin_data_privacy.model.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface DataPrivacyService {

    fun initialize(context: Context)

    fun collectedServices(externalScope: CoroutineScope): Flow<List<Service>>

    fun isReady(): Flow<Boolean>

    fun collectConsent(context: Context)

}