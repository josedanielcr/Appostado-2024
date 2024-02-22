package com.ucenfotec.appostado.core.domain.entities

import com.ucenfotec.appostado.core.domain.common.BaseEntity

data class Dog (
    val name : String?,
    val age : Int?
) : BaseEntity();