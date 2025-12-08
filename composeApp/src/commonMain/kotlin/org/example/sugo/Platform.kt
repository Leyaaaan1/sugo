package org.example.sugo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform