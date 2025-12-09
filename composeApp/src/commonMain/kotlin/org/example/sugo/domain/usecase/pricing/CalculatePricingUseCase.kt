package org.example.sugo.domain.usecase.pricing
import kotlin.math.round
import CampusLocation
import DistanceLevel
import ErrandCategory
import PricingResult
import org.example.sugo.domain.usecase.distance.CalculateDistanceUseCase
import kotlin.math.pow

class CalculatePricingUseCase(
    private val calculateDistance: CalculateDistanceUseCase
) {
    companion object {
        // Base pricing constants
        const val BASE_FEE = 20.0
        const val PER_MINUTE_FEE = 2.0
        const val URGENCY_MULTIPLIER = 1.5
        const val MINIMUM_FEE = 15.0

        // Distance multipliers
        const val VERY_CLOSE_MULTIPLIER = 1.0
        const val SAME_BUILDING_MULTIPLIER = 1.2
        const val NEARBY_MULTIPLIER = 1.5
        const val DIFFERENT_BUILDING_MULTIPLIER = 2.0
        const val MEDIUM_MULTIPLIER = 2.5
        const val FAR_MULTIPLIER = 3.0
        const val OUTSIDE_MULTIPLIER = 4.0

        // Category multipliers
        const val FOOD_MULTIPLIER = 1.1
        const val PRINTING_MULTIPLIER = 1.0
        const val SHOPPING_MULTIPLIER = 1.3
        const val DOCUMENTS_MULTIPLIER = 1.0
        const val DELIVERY_MULTIPLIER = 1.2
        const val PERSONAL_ASSISTANCE_MULTIPLIER = 1.4
        const val OTHER_MULTIPLIER = 1.0
    }

    operator fun invoke(
        category: ErrandCategory,
        pickupLocation: CampusLocation,
        deliveryLocation: CampusLocation,
        isUrgent: Boolean = false
    ): PricingResult {
        // Calculate distance
        val distanceInfo = calculateDistance(pickupLocation, deliveryLocation)

        // Base calculation: base fee + (time * rate)
        var suggestedFee = BASE_FEE + (distanceInfo.estimatedWalkTime * PER_MINUTE_FEE)

        // Apply distance multiplier
        val distanceMultiplier = getDistanceMultiplier(distanceInfo.level)
        suggestedFee *= distanceMultiplier

        // Apply category multiplier
        val categoryMultiplier = getCategoryMultiplier(category)
        suggestedFee *= categoryMultiplier

        // Apply urgency multiplier
        if (isUrgent) {
            suggestedFee *= URGENCY_MULTIPLIER
        }

        // Calculate min and max
        val minimumFee = (suggestedFee * 0.8).coerceAtLeast(MINIMUM_FEE)
        val maximumFee = suggestedFee * 1.5

        return PricingResult(
            suggestedFee = suggestedFee.roundToDecimal(2),
            minimumFee = minimumFee.roundToDecimal(2),
            maximumFee = maximumFee.roundToDecimal(2),
            distanceInfo = distanceInfo
        )
    }

    private fun getDistanceMultiplier(level: DistanceLevel): Double {
        return when (level) {
            DistanceLevel.VERY_CLOSE -> VERY_CLOSE_MULTIPLIER
            DistanceLevel.SAME_BUILDING -> SAME_BUILDING_MULTIPLIER
            DistanceLevel.NEARBY -> NEARBY_MULTIPLIER
            DistanceLevel.DIFFERENT_BUILDING -> DIFFERENT_BUILDING_MULTIPLIER
            DistanceLevel.MEDIUM -> MEDIUM_MULTIPLIER
            DistanceLevel.FAR -> FAR_MULTIPLIER
            DistanceLevel.OUTSIDE -> OUTSIDE_MULTIPLIER
        }
    }

    private fun getCategoryMultiplier(category: ErrandCategory): Double {
        return when (category) {
            ErrandCategory.FOOD -> FOOD_MULTIPLIER
            ErrandCategory.PRINTING -> PRINTING_MULTIPLIER
            ErrandCategory.SHOPPING -> SHOPPING_MULTIPLIER
            ErrandCategory.DOCUMENTS -> DOCUMENTS_MULTIPLIER
            ErrandCategory.DELIVERY -> DELIVERY_MULTIPLIER
            ErrandCategory.PERSONAL_ASSISTANCE -> PERSONAL_ASSISTANCE_MULTIPLIER
            ErrandCategory.OTHER -> OTHER_MULTIPLIER
        }
    }

    private fun Double.roundToDecimal(decimals: Int): Double {
        val multiplier = 10.0.pow(decimals.toDouble())
        return round(this * multiplier) / multiplier
    }
}