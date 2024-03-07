package com.ucenfotec.appostado.core.domain.extensions

import com.google.cloud.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

inline fun <reified T> Map<String, Any?>.getValueOrThrow(key: String): T {
    val value = this[key]

    return when {
        value is Number -> value as T
        value != null -> value as T
        T::class == String::class -> "" as T
        isNumericType<T>() -> when {
            T::class == Int::class -> 0 as T
            T::class == Long::class -> 0L as T
            T::class == Float::class -> 0.0f as T
            T::class == Double::class -> 0.0 as T
            T::class == Byte::class -> 0.toByte() as T
            T::class == Short::class -> 0.toShort() as T
            else -> throw IllegalArgumentException("$key is required and must be a numeric type")
        }
        else -> throw IllegalArgumentException("$key is required and must be a ${T::class.simpleName} or nullable")
    }
}

inline fun <reified T> isNumericType(): Boolean {
    return when (T::class) {
        Int::class, Long::class, Float::class, Double::class, Byte::class, Short::class -> true
        else -> false
    }
}



fun String.toIntOrThrow(fieldName: String): Int {
    return this.toIntOrNull() ?: throw IllegalArgumentException("$fieldName must be a number")
}

fun Timestamp.toLocalDate(): LocalDate {
    val instant = Instant.ofEpochSecond(this.seconds, this.nanos.toLong())
    return instant.atZone(ZoneId.of("UTC")).toLocalDate()
}

fun LocalDate.toTimestamp(): Timestamp {
    val instant = this.atStartOfDay().toInstant(ZoneOffset.UTC)
    return Timestamp.ofTimeSecondsAndNanos(instant.epochSecond, instant.nano)
}