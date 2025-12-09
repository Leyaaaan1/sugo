package org.example.sugo.domain.repository

import User
import UserStats

interface UserRepository {
    suspend fun getUserById(id: String): Result<User>

    suspend fun updateUser(user: User): Result<User>

    suspend fun updateUserRating(userId: String, rating: Double): Result<User>

    suspend fun getUserStats(userId: String): Result<UserStats>

}

