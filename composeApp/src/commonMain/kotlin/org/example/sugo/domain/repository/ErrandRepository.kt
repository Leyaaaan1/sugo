package org.example.sugo.domain.repository

import Errand
import ErrandCategory
import ErrandStatus
import kotlinx.coroutines.flow.Flow

interface ErrandRepository {

    suspend fun createErrand(errand: Errand): org.example.sugo.domain.model.Result<Errand>

    suspend fun getErrandById(id: String): Result<Errand>

    fun getErrands(
        status: ErrandStatus? = null,
        category: ErrandCategory? = null
    ): Flow<List<Errand>>

    fun getErrandsByRequester(requesterId: String): Flow<List<Errand>>

    suspend fun updateErrand(errand: Errand): Result<Errand>

    suspend fun updateErrandStatus(id: String, status: ErrandStatus): Result<Errand>

    suspend fun cancelErrand(id: String): Result<Boolean>

    fun searchErrands(query: String): Flow<List<Errand>>

}

