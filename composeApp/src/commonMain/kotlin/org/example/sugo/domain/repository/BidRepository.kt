package org.example.sugo.domain.repository

import Bid
import BidStatus
import kotlinx.coroutines.flow.Flow

interface BidRepository {
    suspend fun placeBid(bid: Bid): Result<Bid>

    suspend fun getBidById(id: String): Result<Bid>

    fun getBidsByErrand(errandId: String): Flow<List<Bid>>

    fun getBidsByRunner(runnerId: String): Flow<List<Bid>>

    suspend fun updateBidStatus(bidId: String, status: BidStatus, responseMessage: String = ""): Result<Bid>

    suspend fun withdrawBid(bidId: String): Result<Boolean>

    fun getPendingBidsByRunner(runnerId: String): Flow<List<Bid>>
}