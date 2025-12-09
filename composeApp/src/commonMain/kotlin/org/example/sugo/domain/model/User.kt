
data class User(
    val id: String,
    val name: String,
    val role: UserRole,
    val contactInfo: String = "",
    val rating: Double = 0.0,
    val completedErrands: Int = 0
)

enum class UserRole {
    REQUESTER,
    RUNNER,
    BOTH
}

data class UserStats(
    val completedErrands: Int,
    val successfulBids: Int,
    val averageRating: Double,
    val totalEarnings: Double
)
