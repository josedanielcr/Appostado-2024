package com.ucenfotec.appostado.core.application.extensions.authentication

import com.google.cloud.Timestamp
import com.ucenfotec.appostado.core.application.common.exceptions.authentication.InvalidPasswordException
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import com.ucenfotec.appostado.core.application.dtos.user.UserLoginDto
import com.ucenfotec.appostado.core.domain.entities.User
import com.ucenfotec.appostado.core.domain.extensions.toTimestamp

fun createUserEntityByUserAndPassword(user: UserDto, salt : String, hash : String): User {
    val newUser = User(
        name = user.name,
        surname = user.surname,
        email = user.email,
        dateOfBirth = user.dateOfBirth.toTimestamp(),
        passwordHash = hash,
        passwordSalt = salt,
    );
    newUser.createdAt = Timestamp.now();
    newUser.updatedAt = Timestamp.now();
    return newUser;
}

fun validateUserPassword(user: UserLoginDto, userEntity: User, passwordManager : PasswordManager) {
    val isPasswordValid = passwordManager
        .validatePassword(user.password, userEntity.passwordSalt, userEntity.passwordHash);
    if (!isPasswordValid) throw InvalidPasswordException();
}