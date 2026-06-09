package com.project.ecotrace

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform