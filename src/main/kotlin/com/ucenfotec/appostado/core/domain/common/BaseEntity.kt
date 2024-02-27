package com.ucenfotec.appostado.core.domain.common

import com.google.cloud.Timestamp
import java.time.LocalDateTime
import java.util.UUID

open class BaseEntity(
    open var id: String,
    open var createdAt: Timestamp,
    open var updatedAt: Timestamp
)