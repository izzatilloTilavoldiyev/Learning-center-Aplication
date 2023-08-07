package uz.pdp.app.lc.service.homework;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.dto.HomeworkCreateDTO;
import uz.pdp.app.lc.dto.HomeworkUpdateDTO;
import uz.pdp.app.lc.entity.GroupEntity;
import uz.pdp.app.lc.entity.HomeworkEntity;
import uz.pdp.app.lc.entity.UserEntity;
import uz.pdp.app.lc.enums.Role;
import uz.pdp.app.lc.exception.BadRequestException;
import uz.pdp.app.lc.exception.DataNotFoundException;
import uz.pdp.app.lc.repository.GroupRepository;
import uz.pdp.app.lc.repository.HomeworkRepository;
import uz.pdp.app.lc.repository.UserRepository;
import uz.pdp.app.lc.service.group.GroupService;
import uz.pdp.app.lc.service.user.UserService;

import java.util.Objects;

import static uz.pdp.app.lc.mapper.HomeworkMapper.HOMEWORK_MAPPER;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService{

    private final HomeworkRepository homeworkRepository;

    private final GroupService groupService;
    private final GroupRepository groupRepository;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public HomeworkEntity addHomework(HomeworkCreateDTO dto) {
        GroupEntity groupEntity = groupService.getById(dto.groupId());
        UserEntity teacher = userService.getById(dto.createdBy());
        if (!Objects.equals(teacher.getRole(), Role.TEACHER))
            throw new BadRequestException("User not have TEACHER role");
        HomeworkEntity homeworkEntity = HomeworkEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .groupEntity(groupEntity)
                .createdBy(teacher)
                .build();
        return homeworkRepository.save(homeworkEntity);
    }

    @Override
    public HomeworkEntity updateHomework(HomeworkUpdateDTO dto) {
        HomeworkEntity homeworkEntity = getHomeworkById(dto.id());
        return homeworkRepository.save(HOMEWORK_MAPPER.partialUpdate(dto, homeworkEntity));
    }

    @Override
    public void deleteById(Long id) {
        HomeworkEntity homeworkEntity = getHomeworkById(id);
        homeworkEntity.setDeleted(true);
        homeworkRepository.save(homeworkEntity);
    }

    @Override
    public HomeworkEntity getById(Long id) {
        return getHomeworkById(id);
    }

    @Override
    public Page<HomeworkEntity> getAll(int page, int size) {
        return homeworkRepository.findAllHomework(PageRequest.of(page, size));
    }

    @Override
    public Page<HomeworkEntity> getAllDeleted(int page, int size) {
        return homeworkRepository.findAllDeletedHomework(PageRequest.of(page, size));
    }

    @Override
    public Page<HomeworkEntity> getByGroupId(int page, int size, Long groupId) {
        if (!groupRepository.existsGroupById(groupId))
            throw new DataNotFoundException("Group not found with '" + groupId + "' id");
        return homeworkRepository.findHomeworkByGroupId(PageRequest.of(page, size), groupId);
    }

    @Override
    public Page<HomeworkEntity> getByCreatedById(int page, int size, Long createdById) {
        if (!userRepository.teacherExistsById(createdById))
            throw new DataNotFoundException("Teacher not found with '" + createdById + "' id");
        return homeworkRepository.findHomeworkByCreatById(PageRequest.of(page, size), createdById);
    }

    private HomeworkEntity getHomeworkById(Long id) {
        return homeworkRepository.findHomeworkById(id).orElseThrow(
                () -> new DataNotFoundException("Homework not found with '" + id + "' id")
        );
    }
}
