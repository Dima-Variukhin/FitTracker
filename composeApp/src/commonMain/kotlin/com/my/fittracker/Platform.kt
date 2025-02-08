package com.my.fittracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform