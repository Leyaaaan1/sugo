import kotlinx.datetime.LocalDateTime
import kotlin.time.ExperimentalTime

data class Bid @OptIn(ExperimentalTime::class) constructor(
    val id: String,
    val errandId: String,
    val runner: User,
    val proposedPrice: Double,
    val message: String = "",
    val estimatedTime: Int,
    val status: BidStatus,
    val createdAt: LocalDateTime,
    val responseMessage: String = ""
)

enum class BidStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    WITHDRAWN
}