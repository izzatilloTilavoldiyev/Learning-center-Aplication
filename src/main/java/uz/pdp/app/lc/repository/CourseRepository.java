package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    @Query(value = "select count(c.name)>0 from courses c " +
            "where c.name = :name and c.deleted = false ", nativeQuery = true)
    boolean existsByName(String name);

    @Query(value = "select count(c)>0 from courses c " +
            "where c.id =:id and not c.deleted", nativeQuery = true)
    boolean existsById(Long id);

    @Query(value = "from courses c where c.id = :id and c.deleted = false ")
    Optional<CourseEntity> findCourseById(Long id);

    @Query(value = "from courses c where not c.deleted ")
    List<CourseEntity> findAllCourses();

    @Query(value = "from courses c where c.deleted = true ")
    List<CourseEntity> findAllDeletedCourses();

    @Query(value = """
           select * from courses c join
           courses_teachers ct on c.id = ct.course_id where
           ct.teacher_id = :teacherId and not c.deleted
           """, nativeQuery = true)
    List<CourseEntity> findCoursesByTeacherId(Long teacherId);
}

