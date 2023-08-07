package uz.pdp.app.lc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.HomeworkEntity;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {

    @Query(value = "from homework h where h.id =:id and not h.deleted")
    Optional<HomeworkEntity> findHomeworkById(Long id);

    @Query(value = "from homework h where not h.deleted")
    Page<HomeworkEntity> findAllHomework(PageRequest of);

    @Query(value = "from homework h where h.deleted = true ")
    Page<HomeworkEntity> findAllDeletedHomework(PageRequest of);

    @Query(value = "from homework h where h.groupEntity.id = :groupId and not h.deleted")
    Page<HomeworkEntity> findHomeworkByGroupId(PageRequest of, Long groupId);

    @Query(value = "from homework h where h.createdBy.id = :createdById and not h.deleted")
    Page<HomeworkEntity> findHomeworkByCreatById(PageRequest of, Long createdById);
}
