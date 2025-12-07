
object CampusLocations {

    val ALL_LOCATIONS = listOf(
        // ===== CAFETERIAS =====
        CampusLocation(
            id = "caf_main",
            name = "Main Cafeteria",
            building = "Student Center",
            floor = "Ground Floor",
            category = LocationCategory.CAFETERIA,
            zone = CampusZone.CENTER,
            description = "Main food court with 8 food stalls",
            isPopular = true
        ),
        CampusLocation(
            id = "caf_eng",
            name = "Engineering Canteen",
            building = "Engineering Building",
            floor = "1st Floor",
            category = LocationCategory.CAFETERIA,
            zone = CampusZone.NORTH,
            description = "Small canteen near engineering classrooms"
        ),
        CampusLocation(
            id = "caf_med",
            name = "Medical Canteen",
            building = "Medical Building",
            floor = "Ground Floor",
            category = LocationCategory.CAFETERIA,
            zone = CampusZone.SOUTH,
            description = "Healthy food options"
        ),

        // ===== LIBRARIES =====
        CampusLocation(
            id = "lib_main_entrance",
            name = "Main Library Entrance",
            building = "Library Building",
            floor = "Ground Floor",
            category = LocationCategory.LIBRARY,
            zone = CampusZone.CENTER,
            description = "Main entrance and circulation desk",
            isPopular = true
        ),
        CampusLocation(
            id = "lib_3f",
            name = "Library 3rd Floor",
            building = "Library Building",
            floor = "3rd Floor",
            category = LocationCategory.LIBRARY,
            zone = CampusZone.CENTER,
            description = "Quiet study area"
        ),
        CampusLocation(
            id = "lib_basement",
            name = "Library Basement",
            building = "Library Building",
            floor = "Basement",
            category = LocationCategory.LIBRARY,
            zone = CampusZone.CENTER,
            description = "Computer lab and group study rooms"
        ),

        // ===== SHOPS =====
        CampusLocation(
            id = "shop_starbucks",
            name = "Starbucks",
            building = "Student Center",
            floor = "Ground Floor",
            category = LocationCategory.SHOP,
            zone = CampusZone.CENTER,
            description = "Coffee and snacks",
            isPopular = true
        ),
        CampusLocation(
            id = "shop_print",
            name = "Campus Print Shop",
            building = "Admin Building",
            floor = "1st Floor",
            category = LocationCategory.SHOP,
            zone = CampusZone.SOUTH,
            description = "Printing, photocopying, binding services"
        ),
        CampusLocation(
            id = "shop_bookstore",
            name = "University Bookstore",
            building = "Student Center",
            floor = "2nd Floor",
            category = LocationCategory.SHOP,
            zone = CampusZone.CENTER,
            description = "Textbooks and school supplies"
        ),
        CampusLocation(
            id = "shop_convenience",
            name = "7-Eleven",
            building = "Near Main Gate",
            category = LocationCategory.SHOP,
            zone = CampusZone.EAST,
            description = "Convenience store"
        ),

        // ===== DORMS =====
        CampusLocation(
            id = "dorm_north_lobby",
            name = "North Dormitory Lobby",
            building = "North Dorm",
            floor = "Ground Floor",
            category = LocationCategory.DORM,
            zone = CampusZone.NORTH,
            description = "North dorm reception area"
        ),
        CampusLocation(
            id = "dorm_south_lobby",
            name = "South Dormitory Lobby",
            building = "South Dorm",
            floor = "Ground Floor",
            category = LocationCategory.DORM,
            zone = CampusZone.SOUTH,
            description = "South dorm reception area"
        ),

        // ===== CLASSROOM BUILDINGS =====
        CampusLocation(
            id = "class_eng_entrance",
            name = "Engineering Building Entrance",
            building = "Engineering Building",
            category = LocationCategory.CLASSROOM_BUILDING,
            zone = CampusZone.NORTH,
            description = "Main entrance to engineering complex"
        ),
        CampusLocation(
            id = "class_cas_lobby",
            name = "CAS Building Lobby",
            building = "College of Arts & Sciences",
            category = LocationCategory.CLASSROOM_BUILDING,
            zone = CampusZone.EAST,
            description = "Arts and sciences building main lobby"
        ),
        CampusLocation(
            id = "class_business",
            name = "Business Building Entrance",
            building = "Business School",
            category = LocationCategory.CLASSROOM_BUILDING,
            zone = CampusZone.WEST,
            description = "School of Business main entrance"
        ),

        // ===== ADMIN OFFICES =====
        CampusLocation(
            id = "admin_registrar",
            name = "Registrar's Office",
            building = "Admin Building",
            floor = "2nd Floor",
            category = LocationCategory.ADMIN_OFFICE,
            zone = CampusZone.SOUTH,
            description = "Student records and enrollment"
        ),
        CampusLocation(
            id = "admin_cashier",
            name = "Cashier's Office",
            building = "Admin Building",
            floor = "1st Floor",
            category = LocationCategory.ADMIN_OFFICE,
            zone = CampusZone.SOUTH,
            description = "Payment and financial services"
        ),

        // ===== GYM & SPORTS =====
        CampusLocation(
            id = "gym_main",
            name = "Main Gymnasium",
            building = "Sports Complex",
            category = LocationCategory.GYM,
            zone = CampusZone.WEST,
            description = "Main gym and fitness center"
        ),

        // ===== PARKING =====
        CampusLocation(
            id = "parking_main",
            name = "Main Parking Lot",
            building = "Near Main Gate",
            category = LocationCategory.PARKING,
            zone = CampusZone.EAST,
            description = "Student and visitor parking"
        )
    )

    // ===== HELPER FUNCTIONS =====

    fun getById(id: String): CampusLocation? {
        return ALL_LOCATIONS.find { it.id == id }
    }

    fun getByCategory(category: LocationCategory): List<CampusLocation> {
        return ALL_LOCATIONS.filter { it.category == category }
    }

    fun getByZone(zone: CampusZone): List<CampusLocation> {
        return ALL_LOCATIONS.filter { it.zone == zone }
    }

    fun getPopularLocations(): List<CampusLocation> {
        return ALL_LOCATIONS.filter { it.isPopular }
    }

    fun searchByName(query: String): List<CampusLocation> {
        return ALL_LOCATIONS.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.building.contains(query, ignoreCase = true)
        }
    }

    // Get all categories that have at least one location
    fun getAvailableCategories(): List<LocationCategory> {
        return ALL_LOCATIONS.map { it.category }.distinct()
    }
}