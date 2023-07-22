package uz.pdp.app.lc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.app.lc.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "from users u where u.id=:id and u.deleted=false ")
    Optional<UserEntity> findUserById(Long id);

    @Query(value = "from users u where u.deleted = false ")
    Page<UserEntity> findAllUsers(PageRequest of);

    @Query(value = "from users u where u.userRole = 'STUDENT' and u.deleted = false ")
    Page<UserEntity> findAllStudents(PageRequest of);

    @Query(value = "from users u where u.userRole = 'TEACHER' and u.deleted = false ")
    Page<UserEntity> findAllTeachers(PageRequest of);

    @Query(value = "select count(u.id) from users u where u.id = :id and u.deleted = false ")
    int countById(Long id);

    @Query(value = "select count(u.id) from users u where u.id = :id and u.userRole = 'TEACHER' and u.deleted = false ")
    int countByTeacherId(Long id);
}
