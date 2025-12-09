package org.example.sugo.domain.usecase.errand


import CampusLocation
import Errand
import ErrandCategory
import User
import org.example.sugo.domain.repository.ErrandRepository
import org.example.sugo.domain.usecase.pricing.CalculatePricingUseCase
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import org.example.sugo.domain.model.Result

class CreateErrandUseCase(
    private val errandRepository: ErrandRepository,
    private val calculatePricing: CalculatePricingUseCase
) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(
        requester: User,
        description: String,
        category: ErrandCategory,
        pickupLocation: CampusLocation,
        deliveryLocation: CampusLocation,
        deadline: String,
        notes: String = "",
        isUrgent: Boolean = false
    ): Result<Errand> {
        // Validate description
        if (description.isBlank() || description.length < 10) {
            return Result.Error("Description must be at least 10 characters")
        }

        if (description.length > 500) {
            return Result.Error("Description must not exceed 500 characters")
        }

        val pricingResult = calculatePricing(
            category = category,
            pickupLocation = pickupLocation,
            deliveryLocation = deliveryLocation,
            isUrgent = isUrgent
        )

        // Create errand
        val errand = Errand(
            id = generateErrandId(),
            requester = requester,
            description = description,
            category = category,
            pickupLocation = pickupLocation,
            deliveryLocation = deliveryLocation,
            offeredPrice = pricingResult.suggestedFee,
            distanceInfo = pricingResult.distanceInfo,
            deadline = deadline,
            status = ErrandStatus.OPEN,
            notes = notes,
            createdAt = Clock.System.now()
        )

        // Save to repository
        return errandRepository.createErrand(errand)
    }

    @OptIn(ExperimentalTime::class)
    private fun generateErrandId(): String {
        return "ERR_${Clock.System.now().toEpochMilliseconds()}_${(1000..9999).random()}"
    }
}