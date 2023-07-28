package uz.pdp.app.lc.service.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.dto.CourseCreateDTO;
import uz.pdp.app.lc.dto.CourseTeacherDTO;
import uz.pdp.app.lc.dto.CourseUpdateDTO;
import uz.pdp.app.lc.entity.CourseEntity;
import uz.pdp.app.lc.entity.UserEntity;
import uz.pdp.app.lc.exception.DataNotFoundException;
import uz.pdp.app.lc.exception.DuplicateValueException;
import uz.pdp.app.lc.repository.CourseRepository;
import uz.pdp.app.lc.service.user.UserService;

import java.util.List;

import static uz.pdp.app.lc.mapper.CourseMapper.COURSE_MAPPER;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserService userService;

    @Override
    public CourseEntity addCourse(CourseCreateDTO createDTO) {
        if (courseRepository.countByName(createDTO.name()))
            throw new DuplicateValueException("This course already exists");
        return courseRepository.save(COURSE_MAPPER.toEntity(createDTO));
    }

    @Override
    public CourseEntity updateCourse(CourseUpdateDTO updateDTO) {
        return courseRepository.save(COURSE_MAPPER.partialUpdate(updateDTO, getCourseById(updateDTO.id())));
    }

    @Override
    public CourseEntity getById(Long id) {
        return getCourseById(id);
    }

    @Override
    public List<CourseEntity> getAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public void deleteById(Long id) {
        CourseEntity courseEntity = getCourseById(id);
        courseEntity.setDeleted(true);
        courseRepository.save(courseEntity);
    }

    @Override
    public void addTeacherToCourse(CourseTeacherDTO dto) {
        CourseEntity courseEntity = getCourseById(dto.courseID());
        UserEntity teacher = userService.getById(dto.teacherID());
        courseEntity.getTeachers().add(teacher);
        courseRepository.save(courseEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.countById(id) > 0;
    }

    private CourseEntity getCourseById(Long id) {
        return courseRepository.findCourseById(id).orElseThrow(
                () -> new DataNotFoundException("Course not found")
        );
    }
}
