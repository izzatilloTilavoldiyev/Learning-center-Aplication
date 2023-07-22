package uz.pdp.app.lc.service.course;

import uz.pdp.app.lc.dto.CourseCreateDTO;
import uz.pdp.app.lc.dto.CourseTeacherDTO;
import uz.pdp.app.lc.dto.CourseUpdateDTO;
import uz.pdp.app.lc.dto.ResponseDTO;
import uz.pdp.app.lc.entity.CourseEntity;

import java.util.List;

public interface CourseService {

    CourseEntity addCourse(CourseCreateDTO createDTO);

    CourseEntity updateCourse(CourseUpdateDTO updateDTO);

    CourseEntity GetById(Long id);

    List<CourseEntity> getAll();

    void deleteById(Long id);

    void addTeacherToCourse(CourseTeacherDTO dto);

}
