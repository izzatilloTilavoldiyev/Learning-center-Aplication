package uz.pdp.app.lc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    @Query(value = "select count(c.name) from courses c where c.name = :name and c.deleted = false ")
    int countByName(String name);

    @Query(value = "from courses c where c.id = :id and c.deleted = false ")
    Optional<CourseEntity> findCourseById(Long id);

    @Query(value = "from courses c where c.deleted = false ")
    List<CourseEntity> findAllCourses();
}
