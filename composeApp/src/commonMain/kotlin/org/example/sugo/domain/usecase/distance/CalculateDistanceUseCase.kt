package org.example.sugo.domain.usecase.distance

import CampusLocation
import DistanceInfo

class CalculateDistanceUseCase {
    operator fun invoke(
        from: CampusLocation,
        to: CampusLocation
    ): DistanceInfo {
        // Same location
        if (from.id == to.id) {
            return DistanceInfo(
                level = DistanceLevel.VERY_CLOSE,
                description = "Same location",
                estimatedWalkTime = 1
            )
        }

        // Same building, same floor
        if (from.building == to.building && from.floor == to.floor) {
            return DistanceInfo(
                level = DistanceLevel.VERY_CLOSE,
                description = "Same floor in ${from.building}",
                estimatedWalkTime = 2
            )
        }

        // Same building, different floor
        if (from.building == to.building) {
            return DistanceInfo(
                level = DistanceLevel.SAME_BUILDING,
                description = "Different floor in ${from.building}",
                estimatedWalkTime = 5
            )
        }

        // Check if nearby based on categories (food areas)
        val nearbyCategories = setOf(
            LocationCategory.CAFETERIA,
            LocationCategory.CANTEEN
        )
        if (from.category in nearbyCategories && to.category in nearbyCategories) {
            return DistanceInfo(
                level = DistanceLevel.NEARBY,
                description = "Nearby food area",
                estimatedWalkTime = 8
            )
        }

        // Check entrance to campus distance
        val entrances = setOf(LocationCategory.MAA_ENTRANCE, LocationCategory.MATINA_ENTRANCE)
        if ((from.category in entrances && to.category !in entrances) ||
            (to.category in entrances && from.category !in entrances)) {
            return DistanceInfo(
                level = DistanceLevel.MEDIUM,
                description = "Entrance to campus area",
                estimatedWalkTime = 12
            )
        }

        // Check if outside campus
        if (from.category == LocationCategory.OTHER || to.category == LocationCategory.OTHER) {
            return DistanceInfo(
                level = DistanceLevel.OUTSIDE,
                description = "Outside campus delivery",
                estimatedWalkTime = 40
            )
        }

        // Far locations (opposite ends)
        val farCategories = mapOf(
            LocationCategory.MAA_ENTRANCE to LocationCategory.MATINA_ENTRANCE,
            LocationCategory.PARKING_LOT to LocationCategory.LIBRARY,
            LocationCategory.GYM to LocationCategory.ADMIN_OFFICE
        )

        if (farCategories[from.category] == to.category ||
            farCategories[to.category] == from.category) {
            return DistanceInfo(
                level = DistanceLevel.FAR,
                description = "Opposite end of campus",
                estimatedWalkTime = 20
            )
        }

        // Default: different buildings
        return DistanceInfo(
            level = DistanceLevel.DIFFERENT_BUILDING,
            description = "From ${from.building} to ${to.building}",
            estimatedWalkTime = 10
        )
    }
}
