package uz.pdp.app.lc.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.app.lc.dto.GroupCreateDTO;
import uz.pdp.app.lc.dto.GroupUpdateDTO;
import uz.pdp.app.lc.dto.HomeworkCreateDTO;
import uz.pdp.app.lc.dto.HomeworkUpdateDTO;
import uz.pdp.app.lc.entity.GroupEntity;
import uz.pdp.app.lc.entity.HomeworkEntity;

import java.lang.annotation.Inherited;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {

    GroupMapper GROUP_MAPPER = Mappers.getMapper(GroupMapper.class);

    GroupEntity toEntity(GroupCreateDTO createDTO);

    GroupCreateDTO toDto(GroupEntity groupEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupEntity partialUpdate(GroupUpdateDTO groupUpdateDTO, @MappingTarget GroupEntity groupEntity);

}
