package br.com.usercentrics.plugin_data_privacy.service

import android.content.Context
import br.com.usercentrics.plugin_data_privacy.BuildConfig
import br.com.usercentrics.plugin_data_privacy.exception.InitializationException
import br.com.usercentrics.plugin_data_privacy.model.Service
import br.com.usercentrics.plugin_data_privacy.model.toService
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsBanner
import com.usercentrics.sdk.UsercentricsOptions
import com.usercentrics.sdk.UsercentricsServiceConsent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UsercentricsDataPrivacyService : DataPrivacyService {

    private var _services = MutableStateFlow<List<Service>>(emptyList())
    override fun initialize(context: Context) {
        Usercentrics.initialize(
            context,
            UsercentricsOptions(settingsId = BuildConfig.USERCENTRICS_SETTINGS_ID)
        )
    }

    override fun collectedServices(externalScope: CoroutineScope) = _services.shareIn(
        externalScope,
        SharingStarted.WhileSubscribed()
    )

    override fun isReady(): Flow<Boolean> = flow {
        suspendCoroutine {
            Usercentrics.isReady(onSuccess = { status ->
                updateConsent(status.consents)
                it.resume(Unit)
            }) { error ->
                it.resumeWithException(InitializationException(error.message))
            }
        }.also {
            emit(true)
        }
    }.onStart { emit(false) }

    override fun collectConsent(context: Context) = UsercentricsBanner(context).showSecondLayer { response ->
        response?.let {
            updateConsent(it.consents)
        }
    }

    private fun updateConsent(consents: List<UsercentricsServiceConsent>) {
        val serviceIds = consents.filter { it.status }.map { it.templateId }
        _services.update {
            Usercentrics.instance.getCMPData().services.filter { serviceIds.contains(it.templateId) }
                .map {
                    it.toService()
                }
        }
    }
}