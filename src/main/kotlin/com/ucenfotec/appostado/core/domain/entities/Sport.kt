package com.ucenfotec.appostado.core.domain.entities

import com.google.cloud.Timestamp
import com.google.cloud.firestore.DocumentSnapshot
import com.ucenfotec.appostado.core.application.common.exceptions.core.DocumentSnapshotMissingDataException
import com.ucenfotec.appostado.core.domain.common.BaseEntity
import com.ucenfotec.appostado.core.domain.enums.EntityStatus
import com.ucenfotec.appostado.core.domain.extensions.getValueOrThrow
import java.util.UUID

data class Sport(
    val name: String
) : BaseEntity(
    id = UUID.randomUUID().toString(),
    createdAt = null,
    updatedAt = null,
    status = EntityStatus.ACTIVE
) {
    companion object {
        fun fromDocumentSnapshot(documentSnapshot: DocumentSnapshot): Sport {
            val data = documentSnapshot.data
                ?: throw DocumentSnapshotMissingDataException(
                    additionalDetails = mapOf("documentSnapshotId" to documentSnapshot.id)
                )

            return Sport(
                name = data.getValueOrThrow<String>("name")
            ).apply {
                id = documentSnapshot.id
                createdAt = data.getValueOrThrow<Timestamp>("createdAt")
                updatedAt = data.getValueOrThrow<Timestamp>("updatedAt")
                status = data.getValueOrThrow<String>("status").let(EntityStatus::valueOf)
            }
        }
    }
}