package com.ucenfotec.appostado.core.application.services.authentication

import com.google.cloud.Timestamp
import com.ucenfotec.appostado.core.application.common.interfaces.authentication.IAuthenticationService
import com.ucenfotec.appostado.core.application.dtos.user.UserDetailDto
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import com.ucenfotec.appostado.core.application.dtos.user.UserLoginDto
import com.ucenfotec.appostado.core.application.dtos.user.UserLoginResultDto
import com.ucenfotec.appostado.core.application.extensions.authentication.*
import com.ucenfotec.appostado.core.application.extensions.jwt.JwtService
import com.ucenfotec.appostado.core.application.extensions.user.validateNewUser
import com.ucenfotec.appostado.core.application.mappings.user.IUserMapper
import com.ucenfotec.appostado.core.domain.entities.User
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
            val userEntity = userRepository.getUserByEmail(user.email).join();
            validateUserPassword(user, userEntity,passwordManager);
            val token = jwtService.generateToken(userEntity.id);
            val validatedUserEntity = userMapper.userToUserDetailDTO(userEntity);
            UserLoginResultDto(token, validatedUserEntity);
        }
    }
}