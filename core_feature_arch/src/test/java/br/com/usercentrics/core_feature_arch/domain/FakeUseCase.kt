package br.com.usercentrics.core_feature_arch.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUseCase {
    suspend operator fun invoke(): Flow<String> = flow { emit("data") }
}