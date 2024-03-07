package com.ucenfotec.appostado.core.domain.entities

import com.google.cloud.Timestamp
import com.google.cloud.firestore.DocumentSnapshot
import com.ucenfotec.appostado.core.application.common.exceptions.core.DocumentSnapshotMissingDataException
import com.ucenfotec.appostado.core.domain.common.BaseEntity
import com.ucenfotec.appostado.core.domain.extensions.getValueOrThrow
import com.ucenfotec.appostado.core.domain.extensions.toLocalDate
import java.time.LocalDate
import java.util.*

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val profilePictureUrl: String? = null,
    val dateOfBirth: Timestamp,
    var passwordHash: String,
    var passwordSalt: String
) : BaseEntity(
    id = UUID.randomUUID().toString(),
    createdAt = null,
    updatedAt = null
) {
    companion object {
        fun fromDocumentSnapshot(documentSnapshot: DocumentSnapshot): User {
            val data = documentSnapshot.data
                ?: throw DocumentSnapshotMissingDataException(
                    additionalDetails = mapOf("documentSnapshotId" to documentSnapshot.id)
                )

            return User(
                name = data.getValueOrThrow<String>("name"),
                surname = data.getValueOrThrow<String>("surname"),
                email = data.getValueOrThrow<String>("email"),
                profilePictureUrl = data.getValueOrThrow<String?>("profilePictureUrl"),
                dateOfBirth = data.getValueOrThrow<Timestamp>("dateOfBirth"),
                passwordHash = data.getValueOrThrow<String>("passwordHash"),
                passwordSalt = data.getValueOrThrow<String>("passwordSalt")
            ).apply {
                id = documentSnapshot.id
                createdAt = data.getValueOrThrow<Timestamp>("createdAt")
                updatedAt = data.getValueOrThrow<Timestamp>("updatedAt")
            }
        }
    }
}