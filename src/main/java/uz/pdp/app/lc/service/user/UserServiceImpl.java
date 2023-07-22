package uz.pdp.app.lc.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.dto.ProfileUpdateDTO;
import uz.pdp.app.lc.dto.UserCreateDTO;
import uz.pdp.app.lc.dto.UserUpdateDTO;
import uz.pdp.app.lc.entity.UserEntity;
import uz.pdp.app.lc.exception.DataNotFoundException;
import uz.pdp.app.lc.exception.DuplicateValueException;
import uz.pdp.app.lc.repository.UserRepository;

import static uz.pdp.app.lc.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserEntity addUser(UserCreateDTO userDTO) {
        if (userRepository.existsByPhoneNumber(userDTO.phoneNumber()))
            throw new DuplicateValueException("Phone number already exists");
        UserEntity user = USER_MAPPER.toEntity(userDTO);
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserUpdateDTO userUpdateDTO) {
        UserEntity user = getUserById(userUpdateDTO.userId());
        return userRepository.save(USER_MAPPER.partialUpdate(userUpdateDTO, user));
    }

    @Override
    public UserEntity updateProfile(ProfileUpdateDTO profileUpdateDTO) {
        return userRepository.save(USER_MAPPER.partialUpdate(profileUpdateDTO, getUserById(profileUpdateDTO.id())));
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = getUserById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public UserEntity getById(Long id) {
        return getUserById(id);
    }

    @Override
    public Page<UserEntity> getAll(Integer page, Integer size) {
        return userRepository.findAllUsers(PageRequest.of(page, size));
    }

    @Override
    public Page<UserEntity> getAllStudents(Integer page, Integer size) {
        return userRepository.findAllStudents(PageRequest.of(page, size));
    }

    @Override
    public Page<UserEntity> getAllTeachers(Integer page, Integer size) {
        return userRepository.findAllTeachers(PageRequest.of(page, size));
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.countById(id) > 0;
    }

    @Override
    public boolean teacherExistsById(Long id) {
        return userRepository.countByTeacherId(id) > 0;
    }

    private UserEntity getUserById(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(
                () -> new DataNotFoundException("User not found")
        );
    }
}
