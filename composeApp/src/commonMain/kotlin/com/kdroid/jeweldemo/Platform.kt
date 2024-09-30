package com.kdroid.jeweldemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform