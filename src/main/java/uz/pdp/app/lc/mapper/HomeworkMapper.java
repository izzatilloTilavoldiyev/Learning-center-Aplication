package uz.pdp.app.lc.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.app.lc.dto.CourseCreateDTO;
import uz.pdp.app.lc.dto.CourseUpdateDTO;
import uz.pdp.app.lc.dto.HomeworkCreateDTO;
import uz.pdp.app.lc.dto.HomeworkUpdateDTO;
import uz.pdp.app.lc.entity.CourseEntity;
import uz.pdp.app.lc.entity.HomeworkEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HomeworkMapper {

    HomeworkMapper HOMEWORK_MAPPER = Mappers.getMapper(HomeworkMapper.class);

    HomeworkEntity toEntity(HomeworkCreateDTO homeworkCreateDTO);

    HomeworkCreateDTO toDto(HomeworkEntity homeworkEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HomeworkEntity partialUpdate(HomeworkUpdateDTO homeworkUpdateDTO, @MappingTarget HomeworkEntity homeworkEntity);

}
