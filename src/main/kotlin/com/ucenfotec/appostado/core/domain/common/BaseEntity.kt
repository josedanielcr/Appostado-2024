package com.ucenfotec.appostado.core.domain.common

import com.google.cloud.Timestamp

open class BaseEntity(
    open var id: String,
    open var createdAt: Timestamp?,
    open var updatedAt: Timestamp?
)