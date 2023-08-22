package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app.lc.dto.ProfileUpdateDTO;
import uz.pdp.app.lc.dto.ResponseDTO;
import uz.pdp.app.lc.dto.UserUpdateDTO;
import uz.pdp.app.lc.entity.UserEntity;
import uz.pdp.app.lc.service.user.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    /**
     * ---create
     *
     * ---get by id
     * ---get all pages
     * ---get deleted pages
     * ---get all teachers pages
     * ---get all teachers by course id
     * ---get all students pages
     * ---get all students by group id
     * get all students by course id (repo query doesn't work)
     *
     * searching query below
     * select * from users u where lower(u.name) like lower(concat('%', '(my message)', '%'));
     *
     * ---update by id
     * ---update profile
     *
     * ---delete
     */

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserEntity>> getById(@PathVariable Long id) {
        UserEntity userEntity = userService.getById(id);
        return ResponseEntity.ok(new ResponseDTO<>(userEntity));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Page<UserEntity> allPages = userService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allPages));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/all-deleted")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getAllDeleted(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Page<UserEntity> allDeletedPages = userService.getAllDeleted(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allDeletedPages));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/teachers")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getAllTeachers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size
    ) {
        Page<UserEntity> allTeachers = userService.getAllTeachers(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allTeachers));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/teachers-by-course-id/{courseId}")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getTeachersByCourseId(
            @PathVariable Long courseId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Page<UserEntity> teachersByCourseId = userService.getTeachersByCourseId(courseId, page, size);
        return ResponseEntity.ok(new ResponseDTO<>(teachersByCourseId));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/students")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getAllStudents(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size
    ) {
        Page<UserEntity> allStudents = userService.getAllStudents(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allStudents));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/students-by-group-id/{groupId}")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getStudentsByGroupId(
            @PathVariable Long groupId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Page<UserEntity> allStudents = userService.getStudentsByGroupId(groupId, page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allStudents));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/students-by-course-id/{courseId}")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getStudentsByCourseId(
            @PathVariable Long courseId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Page<UserEntity> allStudents = userService.getStudentsByCourseId(courseId, page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allStudents));
    }


    @PreAuthorize(value = "hasRole('MANAGER')")
    @PutMapping()
    public ResponseEntity<ResponseDTO<UserEntity>> updateUser(
            @Valid @RequestBody UserUpdateDTO userUpdateDTO
    ) {
        UserEntity updateUser = userService.updateUser(userUpdateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(updateUser));
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponseDTO<UserEntity>> updateProfile(
            @Valid @RequestBody ProfileUpdateDTO profileUpdateDTO
    ) {
        UserEntity updateProfile = userService.updateProfile(profileUpdateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(updateProfile));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }


}
