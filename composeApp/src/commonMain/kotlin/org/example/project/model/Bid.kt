
data class Bid(
    val id: String,
    val errandId: String,
    val runner: User,
    val proposedPrice: Double,
    val message: String = "",
    val estimatedTime: Int,
    val status: BidStatus,
    val createdAt: Long = System.currentTimeMillis(),
    val responseMessage: String = ""
)

enum class BidStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    WITHDRAWN
}