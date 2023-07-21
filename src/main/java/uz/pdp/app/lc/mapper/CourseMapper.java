package uz.pdp.app.lc.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.app.lc.dto.*;
import uz.pdp.app.lc.entity.CourseEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    CourseMapper COURSE_MAPPER = Mappers.getMapper(CourseMapper.class);

    CourseEntity toEntity(CourseCreateDTO courseCreateDTO);

    CourseCreateDTO toDto(CourseEntity courseEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CourseEntity partialUpdate(CourseUpdateDTO courseUpdateDTO, @MappingTarget CourseEntity courseEntity);

}
