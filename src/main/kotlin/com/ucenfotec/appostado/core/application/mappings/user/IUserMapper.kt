package com.ucenfotec.appostado.core.application.mappings.user

import com.ucenfotec.appostado.core.application.dtos.user.UserDetailDto
import com.ucenfotec.appostado.core.domain.entities.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface IUserMapper {
    fun userDetailDTOToUser(userDetailDTO: UserDetailDto): User
    fun userToUserDetailDTO(user: User): UserDetailDto
}