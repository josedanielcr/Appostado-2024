package com.ucenfotec.appostado.core.domain.extensions

import com.google.cloud.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

inline fun <reified T> Map<String, Any>.getValueOrThrow(key: String): T {
    return this[key] as? T ?: throw IllegalArgumentException("$key is required and must be a ${T::class.simpleName}")
}

fun String.toIntOrThrow(fieldName: String): Int {
    return this.toIntOrNull() ?: throw IllegalArgumentException("$fieldName must be a number")
}