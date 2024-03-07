package com.ucenfotec.appostado.core.application.services.authentication

import com.google.cloud.Timestamp
import com.ucenfotec.appostado.core.application.common.exceptions.authentication.PasswordsDoNotMatchException
import com.ucenfotec.appostado.core.application.common.exceptions.user.UserNotFoundException
import com.ucenfotec.appostado.core.application.common.interfaces.authentication.IAuthenticationService
import com.ucenfotec.appostado.core.application.dtos.user.*
import com.ucenfotec.appostado.core.application.extensions.authentication.*
import com.ucenfotec.appostado.core.application.extensions.jwt.JwtService
import com.ucenfotec.appostado.core.application.extensions.user.validateNewUser
import com.ucenfotec.appostado.core.application.mappings.user.IUserMapper
import com.ucenfotec.appostado.core.domain.enums.EntityStatus
import com.ucenfotec.appostado.infrastructure.repositories.user.IUserRepository
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class AuthenticationService(
    val userMapper : IUserMapper,
    val userRepository: IUserRepository,
    val passwordManager: PasswordManager,
    val jwtService: JwtService
): IAuthenticationService {

    override fun signUp(user: UserDto): CompletableFuture<UserDetailDto> {
        return CompletableFuture.supplyAsync {
            validateSignUpUser(user, userRepository);
            validateNewUser(user);
            val(salt, hash) = passwordManager.generateSaltAndHash(user.password);
            val userEntity = createUserEntityByUserAndPassword(user,salt,hash)
            val createdUser = userRepository.createUser(userEntity).join();
            userMapper.userToUserDetailDTO(createdUser)
        }
    }

    override fun signIn(user: UserLoginDto): CompletableFuture<UserLoginResultDto> {
        return CompletableFuture.supplyAsync {
            val userEntity = userRepository.getUserByEmail(user.email).join() ?: throw UserNotFoundException();
            validateUserPassword(user.password, userEntity.passwordSalt, userEntity.passwordHash,passwordManager);
            val token = jwtService.generateToken(userEntity.id);
            val validatedUserEntity = userMapper.userToUserDetailDTO(userEntity);
            UserLoginResultDto(token, validatedUserEntity);
        }
    }

    override fun updatePassword(user: UpdateUserPasswordDto): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            if(user.newPassword != user.confirmNewPassword) throw PasswordsDoNotMatchException();

            val userEntity = userRepository.getUserById(user.userId).join() ?: throw UserNotFoundException();
            validateUserPassword(user.oldPassword, userEntity.passwordSalt, userEntity.passwordHash, passwordManager);
            validatePasswordComplexity(user.newPassword);
            val (salt, hash) = passwordManager.generateSaltAndHash(user.newPassword);
            userEntity.passwordSalt = salt;
            userEntity.passwordHash = hash;
            userEntity.updatedAt = Timestamp.now();
            userRepository.updateUser(userEntity.id, userEntity).join();
            true;
        }
    }

    override fun deleteUser(userId: String): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            val userEntity = userRepository.getUserById(userId).join() ?: throw UserNotFoundException();
            userEntity.status = EntityStatus.INACTIVE;
            userEntity.updatedAt = Timestamp.now();
            userRepository.updateUser(userId, userEntity).join();
            true;
        }
    }
}