package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.GroupEntity;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query(value = "select count(g.id) from groups g where g.id =:id and g.deleted = false ")
    int countByName(Long id);

    @Query(value = "from groups g where g.id =:id and g.deleted = false ")
    Optional<GroupEntity> findGroupById(Long id);

    @Query(value = "from groups g where g.deleted = false ")
    List<GroupEntity> findAllGroups();

    @Query(value = "from groups g where g.courseEntity =:id and g.deleted = false ")
    List<GroupEntity> findGroupsByCourseId(Long id);

    @Query(value = "from groups g where g.userEntity =:id and g.deleted = false ")
    List<GroupEntity> findGroupsByTeacherId(Long id);
}
