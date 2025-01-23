package com.virtualworld.mipymeanabel.data.utils

import kotlin.random.Random

fun generateRandomCode(): String {
    val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return buildString {
        repeat(6) {
            append(characters[Random.nextInt(characters.length)])
        }
    }
}