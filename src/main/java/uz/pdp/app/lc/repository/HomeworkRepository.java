package uz.pdp.app.lc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.HomeworkEntity;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {

    @Query(value = "from homework h where h.id =:id and h.deleted = false ")
    Optional<HomeworkEntity> findHomeworkById(Long id);

    @Query(value = "from homework h where h.deleted = false ")
    Page<HomeworkEntity> findAllHomework(PageRequest of);

    @Query(value = "from homework h where h.groupEntity =:groupId and h.deleted = false ")
    Page<HomeworkEntity> findHomeworkByGroupId(PageRequest of, Long groupId);
}
