package com.ucenfotec.appostado.core.application.mappings.dog

import com.ucenfotec.appostado.core.application.dtos.dog.DogDTO
import com.ucenfotec.appostado.core.application.dtos.dog.DogDetailDTO
import com.ucenfotec.appostado.core.domain.entities.Dog
import org.mapstruct.Mapper
@Mapper(componentModel = "spring")
interface IDogMapper {
    fun dogToDogDetailDTO(dog: Dog): DogDetailDTO
    fun dogDTOToDog(dogDTO: DogDTO): Dog
    fun dogDetailDTOToDog(dogDetailDTO: DogDetailDTO): Dog
}