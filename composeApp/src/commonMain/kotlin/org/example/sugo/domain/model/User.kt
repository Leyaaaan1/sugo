

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