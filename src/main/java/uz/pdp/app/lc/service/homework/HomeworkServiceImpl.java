package uz.pdp.app.lc.service.homework;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.dto.HomeworkCreateDTO;
import uz.pdp.app.lc.dto.HomeworkUpdateDTO;
import uz.pdp.app.lc.entity.HomeworkEntity;
import uz.pdp.app.lc.exception.DataNotFoundException;
import uz.pdp.app.lc.repository.HomeworkRepository;

import java.util.List;

import static uz.pdp.app.lc.mapper.HomeworkMapper.HOMEWORK_MAPPER;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService{

    private final HomeworkRepository homeworkRepository;

    @Override
    public HomeworkEntity addHomework(HomeworkCreateDTO dto) {
        //
        return homeworkRepository.save(HOMEWORK_MAPPER.toEntity(dto));
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
    public Page<HomeworkEntity> getByGroupId(int page, int size, Long groupId) {
        return homeworkRepository.findHomeworkByGroupId(PageRequest.of(page, size), groupId);
    }

    private HomeworkEntity getHomeworkById(Long id) {
        return homeworkRepository.findHomeworkById(id).orElseThrow(
                () -> new DataNotFoundException("Homework not found")
        );
    }
}
