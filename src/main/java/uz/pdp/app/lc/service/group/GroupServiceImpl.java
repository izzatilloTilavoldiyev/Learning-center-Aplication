package uz.pdp.app.lc.service.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.dto.GroupCreateDTO;
import uz.pdp.app.lc.dto.GroupUpdateDTO;
import uz.pdp.app.lc.entity.CourseEntity;
import uz.pdp.app.lc.entity.GroupEntity;
import uz.pdp.app.lc.entity.UserEntity;
import uz.pdp.app.lc.enums.Role;
import uz.pdp.app.lc.exception.BadRequestException;
import uz.pdp.app.lc.exception.DataNotFoundException;
import uz.pdp.app.lc.exception.DuplicateValueException;
import uz.pdp.app.lc.repository.GroupRepository;
import uz.pdp.app.lc.service.course.CourseService;
import uz.pdp.app.lc.service.user.UserService;

import java.util.List;
import java.util.Objects;

import static uz.pdp.app.lc.mapper.GroupMapper.GROUP_MAPPER;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    private final CourseService courseService;

    private final UserService userService;

    @Override
    public GroupEntity addGroup(GroupCreateDTO dto) {
        CourseEntity courseEntity = courseService.getById(dto.courseId());
        if (groupRepository.existsByName(dto.name()))
            throw new DuplicateValueException("This group already exists with name '" + dto.name() + "'");

        UserEntity teacher = userService.getById(dto.teacherId());
        if (!Objects.equals(teacher.getRole(), Role.TEACHER))
            throw new BadRequestException("This user has not TEACHER role");

        GroupEntity group = GROUP_MAPPER.toEntity(dto);
        group.setCourseEntity(courseEntity);
        group.setUserEntity(teacher);
        return groupRepository.save(group);
    }

    @Override
    public void addStudent(Long groupId, Long studentId) {
        GroupEntity groupEntity = getGroupById(groupId);
        UserEntity student = userService.getById(studentId);
        if (!Objects.equals(student.getRole(), Role.STUDENT))
            throw new BadRequestException("User has not STUDENT role");
        if (groupRepository.studentExistsById(studentId))
            throw new DuplicateValueException(
                    "Student already exists in this group with '" + studentId + "' id");
        groupEntity.getStudents().add(student);
        groupRepository.save(groupEntity);
    }

    @Override
    public GroupEntity updateGroup(GroupUpdateDTO dto) {
        GroupEntity groupEntity = getGroupById(dto.id());
        return groupRepository.save(GROUP_MAPPER.partialUpdate(dto, groupEntity));
    }

    @Override
    public void deleteById(Long id) {
        GroupEntity groupEntity = getGroupById(id);
        groupEntity.setDeleted(true);
        groupRepository.save(groupEntity);
    }

    @Override
    public GroupEntity getById(Long id) {
        return getGroupById(id);
    }

    @Override
    public List<GroupEntity> getAll() {
        return groupRepository.findAllGroups();
    }

    @Override
    public List<GroupEntity> getGroupByCourseId(Long id) {
        if (!courseService.existsById(id))
            throw new DataNotFoundException("Course not found");
        return groupRepository.findGroupsByCourseId(id);
    }

    @Override
    public List<GroupEntity> getGroupByTeacherId(Long id) {
        if (!userService.teacherExistsById(id))
            throw new DataNotFoundException("Teacher not found");
        return groupRepository.findGroupsByTeacherId(id);
    }

    @Override
    public boolean existsById(Long id) {
        return groupRepository.countByName(id) > 0;
    }

    private GroupEntity getGroupById(Long id) {
        return groupRepository.findGroupById(id).orElseThrow(
                () -> new DataNotFoundException("Group not found with '" + id + "' id")
        );
    }
}
