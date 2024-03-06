package com.ucenfotec.appostado.core.application.extensions.authentication

import com.google.cloud.Timestamp
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import com.ucenfotec.appostado.core.domain.entities.User
import com.ucenfotec.appostado.core.domain.extensions.toTimestamp

fun createUserEntityByUserAndPassword(user: UserDto, salt : String, hash : String): User {
    val user = User(
        name = user.name,
        surname = user.surname,
        email = user.email,
        dateOfBirth = user.dateOfBirth.toTimestamp(),
        passwordHash = hash,
        passwordSalt = salt,
    );
    user.createdAt = Timestamp.now();
    user.updatedAt = Timestamp.now();
    return user;
}