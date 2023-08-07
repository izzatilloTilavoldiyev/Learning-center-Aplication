package uz.pdp.app.lc.service.group;

import uz.pdp.app.lc.dto.GroupCreateDTO;
import uz.pdp.app.lc.dto.GroupUpdateDTO;
import uz.pdp.app.lc.entity.GroupEntity;

import java.util.List;

public interface GroupService {

    GroupEntity addGroup(GroupCreateDTO dto);

    void addStudent(Long groupId, Long studentId);

    GroupEntity getById(Long id);

    List<GroupEntity> getAll();
    List<GroupEntity> getAllDeleted();

    List<GroupEntity> getGroupByCourseId(Long id);

    List<GroupEntity> getTeacherGroups(Long id);

    List<GroupEntity> getStudentGroups(Long studentId);

    GroupEntity updateGroup(GroupUpdateDTO dto);

    GroupEntity changeTeacher(Long groupId, Long teacherOldId, Long teacherNewId);

    void deleteById(Long id);

    void deleteStudent(Long groupId, Long studentId);

}
