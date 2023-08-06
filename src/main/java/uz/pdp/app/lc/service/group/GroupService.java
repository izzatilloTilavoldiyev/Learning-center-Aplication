package uz.pdp.app.lc.service.group;

import uz.pdp.app.lc.dto.GroupCreateDTO;
import uz.pdp.app.lc.dto.GroupUpdateDTO;
import uz.pdp.app.lc.entity.GroupEntity;

import java.util.List;

public interface GroupService {

    GroupEntity addGroup(GroupCreateDTO dto);

    GroupEntity updateGroup(GroupUpdateDTO dto);

    void deleteById(Long id);

    GroupEntity getById(Long id);

    List<GroupEntity> getAll();

    List<GroupEntity> getGroupByCourseId(Long id);

    List<GroupEntity> getGroupByTeacherId(Long id);

    void addStudent(Long groupId, Long studentId);

    boolean existsById(Long id);
}
