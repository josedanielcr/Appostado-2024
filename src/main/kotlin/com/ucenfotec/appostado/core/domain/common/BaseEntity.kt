package com.ucenfotec.appostado.core.domain.common

import java.time.LocalDateTime
import java.util.UUID

open class BaseEntity {
    val id : UUID = UUID.randomUUID();
    val createdAt : LocalDateTime = LocalDateTime.now();
    val updatedAt : LocalDateTime = LocalDateTime.now();
}