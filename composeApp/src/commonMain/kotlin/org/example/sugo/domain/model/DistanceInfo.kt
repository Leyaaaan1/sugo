data class DistanceInfo(
    val level: DistanceLevel,
    val description: String,              // "Same floor", "Across campus"
    val estimatedWalkTime: Int            // Minutes
)

enum class DistanceLevel(
    val description: String,
    val walkTimeMinutes: Int
) {
    VERY_CLOSE("Same floor", 2),
    SAME_BUILDING("Different floor", 5),
    NEARBY("Same area", 8),
    DIFFERENT_BUILDING("Different building", 10),
    MEDIUM("Across campus", 12),
    FAR("Opposite end of campus", 20),
    OUTSIDE("Outside campus", 40)


}