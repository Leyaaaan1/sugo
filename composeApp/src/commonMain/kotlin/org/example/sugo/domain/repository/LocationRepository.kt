package org.example.sugo.domain.repository

import CampusLocation
import LocationCategory

interface LocationRepository {

    suspend fun getAllLocations(): Result<List<CampusLocation>>

    suspend fun getLocationsByCategory(category: LocationCategory): Result<List<CampusLocation>>

    suspend fun searchLocations(query: String): Result<List<CampusLocation>>

    suspend fun getLocationById(id: String): Result<CampusLocation>
}