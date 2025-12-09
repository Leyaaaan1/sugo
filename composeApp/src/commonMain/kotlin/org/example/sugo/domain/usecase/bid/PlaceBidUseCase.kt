package org.example.sugo.domain.usecase.bid

import Bid
import Errand
import TimeUtil
import User
import org.example.sugo.domain.repository.BidRepository
import kotlin.time.ExperimentalTime
import org.example.sugo.domain.model.Result
class PlaceBidUseCase(
    private val bidRepository: BidRepository,
    private val timeUtil: TimeUtil
) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(
        errand: Errand,
        runner: User,
        proposedPrice: Double,
        estimatedTime: Int,
        message: String = ""
    ): Result<Bid> {
        // Validate errand status
        if (errand.status != ErrandStatus.OPEN && errand.status != ErrandStatus.BIDDING) {
            return Result.Error("This errand is not accepting bids")
        }

        // Validate runner is not the requester
        if (runner.id == errand.requester.id) {
            return Result.Error("You cannot bid on your own errand")
        }

        // Validate price
        if (proposedPrice < 15.0) {
            return Result.Error("Bid price must be at least ₱15")
        }

        if (proposedPrice > 1000.0) {
            return Result.Error("Bid price cannot exceed ₱1000")
        }

        // Validate estimated time
        if (estimatedTime !in 1..300) {
            return Result.Error("Estimated time must be between 1 and 300 minutes")
        }

        // Validate message length
        if (message.length > 200) {
            return Result.Error("Message must not exceed 200 characters")
        }

        // Check if runner already has a pending bid
        val existingBid = errand.bids.find {
            it.runner.id == runner.id && it.status == BidStatus.PENDING
        }
        if (existingBid != null) {
            return Result.Error("You already have a pending bid on this errand")
        }

        // Create bid
        val bid = Bid(
            id = generateBidId(),
            errandId = errand.id,
            runner = runner,
            proposedPrice = proposedPrice,
            message = message,
            estimatedTime = estimatedTime,
            status = BidStatus.PENDING,
            createdAt = timeUtil.getCurrentTime()
        )

        // Save bid
        return bidRepository.placeBid(bid)
    }

    @OptIn(ExperimentalTime::class)
    private fun generateBidId(): String {
        val timestamp = TimeUtil
            .getCurrentInstant()
            .toEpochMilliseconds()

        return "BID_${timestamp}_${(1000..9999).random()}"
    }
}