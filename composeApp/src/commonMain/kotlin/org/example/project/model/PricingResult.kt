data class PricingResult(
    val suggestedFee: Double,             // Fair price
    val minimumFee: Double,               // 20% lower (negotiation room)
    val maximumFee: Double,               // 50% higher (if urgent)
    val distanceInfo: DistanceInfo
)