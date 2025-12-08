import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Bid @OptIn(ExperimentalTime::class) constructor(
    val id: String,
    val errandId: String,
    val runner: User,
    val proposedPrice: Double,
    val message: String = "",
    val estimatedTime: Int,
    val status: BidStatus,
    val createdAt: Instant = Clock.System.now(),
    val responseMessage: String = ""
)

enum class BidStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    WITHDRAWN
}