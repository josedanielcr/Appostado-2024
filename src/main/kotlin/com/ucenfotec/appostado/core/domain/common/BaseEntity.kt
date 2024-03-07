package com.ucenfotec.appostado.core.domain.common

import com.google.cloud.Timestamp
import com.ucenfotec.appostado.core.domain.enums.EntityStatus

open class BaseEntity(
    open var id: String,
    open var createdAt: Timestamp?,
    open var updatedAt: Timestamp?,
    open var status : EntityStatus
)