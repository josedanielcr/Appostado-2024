package com.ucenfotec.appostado.core.domain.entities

import com.google.cloud.Timestamp
import com.google.cloud.firestore.DocumentSnapshot
import com.ucenfotec.appostado.core.application.common.exceptions.DocumentSnapshotMissingDataException
import com.ucenfotec.appostado.core.domain.common.BaseEntity
import com.ucenfotec.appostado.core.domain.extensions.getValueOrThrow
import java.util.UUID

data class Dog(
    val name: String,
    val age: Int
) : BaseEntity(
    id = UUID.randomUUID().toString(),
    createdAt = Timestamp.now(),
    updatedAt = Timestamp.now()
) {
    companion object {
        fun fromDocumentSnapshot(documentSnapshot: DocumentSnapshot): Dog {
            val data = documentSnapshot.data
                ?: throw DocumentSnapshotMissingDataException(
                    additionalDetails = mapOf("documentSnapshotId" to documentSnapshot.id)
                )

            return Dog(
                name = data.getValueOrThrow<String>("name"),
                age = data.getValueOrThrow<Int>("age")
            ).apply {
                id = documentSnapshot.id
                createdAt = data.getValueOrThrow<Timestamp>("createdAt")
                updatedAt = data.getValueOrThrow<Timestamp>("updatedAt")
            }
        }
    }
}