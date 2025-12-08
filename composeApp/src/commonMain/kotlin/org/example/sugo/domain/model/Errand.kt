import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Errand @OptIn(ExperimentalTime::class) constructor(
    val id: String,
    val requester: User,
    val description: String,
    val category: ErrandCategory,
    val pickupLocation: CampusLocation,
    val deliveryLocation: CampusLocation,
    val offeredPrice: Double,
    val distanceInfo: DistanceInfo,
    val deadline: String,
    val status: ErrandStatus,
    val bids: List<Bid> = emptyList(),
    val acceptedBid: Bid? = null,
    val notes: String = "",
    val createdAt: Instant = Clock.System.now(),

    )

enum class ErrandCategory {
    FOOD,
    PRINTING,
    SHOPPING,
    DOCUMENTS,
    DELIVERY,
    PERSONAL_ASSISTANCE,
    OTHER
}

enum class ErrandStatus {
    OPEN,           // Just posted, waiting for bids
    BIDDING,        // Receiving bids from runners
    ACCEPTED,       // A bid was accepted
    IN_PROGRESS,    // Runner is doing the task
    COMPLETED,      // Task finished
    CANCELLED       // Requester cancelled
}