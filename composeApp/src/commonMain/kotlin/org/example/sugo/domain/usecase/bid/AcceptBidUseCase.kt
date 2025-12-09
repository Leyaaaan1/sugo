package org.example.sugo.domain.usecase.bid

import Bid
import Errand
import org.example.sugo.domain.repository.BidRepository
import org.example.sugo.domain.repository.ErrandRepository
import org.example.sugo.domain.model.Result

class AcceptBidUseCase(
    private val bidRepository: BidRepository,
    private val errandRepository: ErrandRepository
) {
    suspend operator fun invoke(
        errand: Errand,
        bid: Bid,
        responseMessage: String = ""
    ): Result<Pair<Errand, Bid>> {
        // Validate bid status
        if (bid.status != BidStatus.PENDING) {
            return Result.Error("This bid is not pending")
        }

        // Validate errand status
        if (errand.status != ErrandStatus.OPEN && errand.status != ErrandStatus.BIDDING) {
            return Result.Error("This errand cannot accept bids")
        }

        // Update bid to accepted
        val acceptedBidResult = bidRepository.updateBidStatus(
            bidId = bid.id,
            status = BidStatus.ACCEPTED,
            responseMessage = responseMessage
        )

        if (acceptedBidResult is Result.Error) {
            return acceptedBidResult
        }

        val acceptedBid = (acceptedBidResult as Result.Success).data

        // Update errand with accepted bid
        val updatedErrand = errand.copy(
            status = ErrandStatus.ACCEPTED,
            acceptedBid = acceptedBid
        )

        val errandResult = errandRepository.updateErrand(updatedErrand)

        if (errandResult is Result.Error) {
            return errandResult
        }

        // Reject all other pending bids
        errand.bids
            .filter { it.id != bid.id && it.status == BidStatus.PENDING }
            .forEach { otherBid ->
                bidRepository.updateBidStatus(
                    bidId = otherBid.id,
                    status = BidStatus.REJECTED,
                    responseMessage = "Another bid was accepted"
                )
            }

        return Result.Success(Pair((errandResult as Result.Success).data, acceptedBid))
    }
}