package uz.pdp.app.lc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "from users u where u.phoneNumber = :phoneNumber and not u.deleted")
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    @Query(value = "from users u where u.id = :id and not u.deleted")
    Optional<UserEntity> findUserById(Long id);

    @Query(value = "from users u where not u.deleted")
    Page<UserEntity> findAllUsers(PageRequest of);

    @Query(value = "from users u where u.deleted = true ")
    Page<UserEntity> findAllDeletedUsers(PageRequest of);

    @Query(value = "from users u where u.role = 'TEACHER' and not u.deleted")
    Page<UserEntity> findAllTeachers(PageRequest of);

    @Query(value = """
           select * from users u join 
           courses_teachers ct on u.id = ct.teacher_id 
           where ct.course_id = :courseId and not u.deleted
           """, nativeQuery = true)
    Page<UserEntity> findTeachersByCourseId(Long courseId, PageRequest of);

    @Query(value = "from users u where u.role = 'STUDENT' and not u.deleted")
    Page<UserEntity> findAllStudents(PageRequest of);

    @Query(value = """
           select * from users u join 
           groups_students gs on u.id = gs.student_id 
           where gs.group_id = :groupId and not u.deleted
           """, nativeQuery = true)
    Page<UserEntity> findStudentsByGroupId(Long groupId, PageRequest of);

    @Query(value = """
           select * from users u join 
           groups_students gs on u.id = gs.student_id
           join groups g on gs.group_id = g.id
           where g.course_id = :courseId
           and not u.deleted
           """, nativeQuery = true)
    Page<UserEntity> findStudentsByCourseId(Long courseId, PageRequest of);

    @Query(value = "select count(u.id)>0 from users u where u.id = :id " +
            "and u.role = 'TEACHER' and not u.deleted", nativeQuery = true)
    boolean teacherExistsById(Long id);

    @Query(value = "select count(u.id)>0 from users u where u.id = :id " +
            "and u.role = 'STUDENT' and not u.deleted", nativeQuery = true)
    boolean studentExistsById(Long id);
}
