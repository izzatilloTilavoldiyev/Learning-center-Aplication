package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.app.lc.entity.GroupEntity;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query(value = "select count(g.name)>0 from groups g " +
            "where g.name = :name and not g.deleted", nativeQuery = true)
    boolean existsByName(String name);

    @Query(value = """
            select count(g.id) > 0 from groups g 
            join groups_students gs on g.id = gs.group_id 
            where gs.student_id = :studentId
            and not g.deleted
            """, nativeQuery = true)
    boolean studentExistsById(Long studentId);

    @Query(value = "from groups g where g.id = :id and not g.deleted")
    Optional<GroupEntity> findGroupById(Long id);

    @Query(value = "from groups g where not g.deleted ")
    List<GroupEntity> findAllGroups();

    @Query(value = "from groups g where g.deleted = true ")
    List<GroupEntity> findAllDeleted();

    @Query(value = "from groups g where g.courseEntity.id = :id and not g.deleted")
    List<GroupEntity> findGroupsByCourseId(Long id);

    @Query(value = "from groups g where g.userEntity.id = :id and not g.deleted")
    List<GroupEntity> findAllByTeacherId(Long id);

    @Query(
            value = """
                    select * from groups g join groups_students gs on g.id = gs.group_id
                    where gs.student_id = :studentId and not g.deleted
                    """, nativeQuery = true
    )
//    @Query(value = "from groups g join g.students s where s.id = :studentId and not g.deleted")
    List<GroupEntity> findAllByStudentId(@Param("studentId") Long studentId);

    @Query(
            value = """
                    select count(g.id) > 0 from groups g 
                    where g.id = :groupId
                    and g.teacher_id = :teacherId
                    and not g.deleted
                    """, nativeQuery = true
    )
    boolean teacherExistsInGroup(Long groupId, Long teacherId);

    @Query(
            value = """
                    select count(g.id) > 0 from groups g 
                    join groups_students gs on g.id = gs.group_id
                    where gs.group_id = :groupId 
                    and gs.student_id = :studentId
                    and not g.deleted
                    """, nativeQuery = true
    )
    boolean studentExistsInGroup(Long groupId, Long studentId);

    @Query(
            value = """
                    select count(g.id) > 0 from groups g 
                    where g.id = :groupId
                    and not g.deleted
                    """, nativeQuery = true
    )
    boolean existsGroupById(Long groupId);

}
