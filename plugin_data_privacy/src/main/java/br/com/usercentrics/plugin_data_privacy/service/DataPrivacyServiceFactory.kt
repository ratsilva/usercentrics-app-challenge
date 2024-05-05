package br.com.usercentrics.plugin_data_privacy.service

interface DataPrivacyServiceFactory {
    fun create(): DataPrivacyService
}

class DefaultDataPrivacyServiceFactory : DataPrivacyServiceFactory {
    override fun create() = UsercentricsDataPrivacyService()

}