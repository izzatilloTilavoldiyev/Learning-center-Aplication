package uz.pdp.app.lc.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.app.lc.dto.ProfileUpdateDTO;
import uz.pdp.app.lc.dto.UserCreateDTO;
import uz.pdp.app.lc.dto.UserUpdateDTO;
import uz.pdp.app.lc.entity.UserEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userRole", source = "role")
    UserEntity toEntity(UserCreateDTO userCreateDTO);


    @InheritInverseConfiguration(name = "toEntity")
    UserCreateDTO toDto(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserUpdateDTO userUpdateDTO, @MappingTarget UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(ProfileUpdateDTO profileUpdateDTO, @MappingTarget UserEntity userEntity);

}
