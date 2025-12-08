data class CampusLocation(
    val id: String,
    val name: String,
    val building: String,
    val floor: String? = null,
    val category: LocationCategory,
    val description: String = "",
)

enum class LocationCategory {
    CAFETERIA,
    LIBRARY,
    BUILDING,
    CLASSROOM_BUILDING,
    ADMIN_OFFICE,
    CANTEEN,
    GYM,
    MAA_ENTRANCE,
    MATINA_ENTRANCE,
    PARKING_LOT,
    OVAL,
    MINI_FOREST,
    OTHER
}