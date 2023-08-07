package uz.pdp.app.lc.service.homework;

import org.springframework.data.domain.Page;
import uz.pdp.app.lc.dto.HomeworkCreateDTO;
import uz.pdp.app.lc.dto.HomeworkUpdateDTO;
import uz.pdp.app.lc.entity.HomeworkEntity;

import java.util.List;

public interface HomeworkService {

    HomeworkEntity addHomework(HomeworkCreateDTO dto);

    HomeworkEntity updateHomework(HomeworkUpdateDTO dto);

    void deleteById(Long id);

    HomeworkEntity getById(Long id);

    Page<HomeworkEntity> getAll(int page, int size);
    Page<HomeworkEntity> getAllDeleted(int page, int size);

    Page<HomeworkEntity> getByGroupId(int page, int size, Long groupId);

}
