package uz.pdp.app.lc.service.user;

import org.springframework.data.domain.Page;
import uz.pdp.app.lc.dto.ProfileUpdateDTO;
import uz.pdp.app.lc.dto.UserUpdateDTO;
import uz.pdp.app.lc.entity.UserEntity;

public interface UserService {

    UserEntity getById(Long id);

    Page<UserEntity> getAll(Integer page, Integer size);

    Page<UserEntity> getAllDeleted(Integer page, Integer size);

    Page<UserEntity> getAllStudents(Integer page, Integer size);

    Page<UserEntity> getAllTeachers(Integer page, Integer size);

    UserEntity updateUser(UserUpdateDTO userUpdateDTO);

    UserEntity updateProfile(ProfileUpdateDTO profileUpdateDTO);

    void deleteUser(Long id);

}
