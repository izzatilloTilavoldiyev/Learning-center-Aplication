package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app.lc.dto.ProfileUpdateDTO;
import uz.pdp.app.lc.dto.ResponseDTO;
import uz.pdp.app.lc.dto.UserCreateDTO;
import uz.pdp.app.lc.dto.UserUpdateDTO;
import uz.pdp.app.lc.entity.UserEntity;
import uz.pdp.app.lc.service.user.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<UserEntity>> addUser(
            @Valid @RequestBody UserCreateDTO userCreateDTO
    ) {
        UserEntity user = userService.addUser(userCreateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(user));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<UserEntity>> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(userService.updateUser(userUpdateDTO)));
    }

    @PutMapping("/update/profile")
    public ResponseEntity<ResponseDTO<UserEntity>> updateUser(@RequestBody ProfileUpdateDTO profileUpdateDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(userService.updateProfile(profileUpdateDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<UserEntity>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(userService.getById(id)));
    }

    @GetMapping("/get/all-page")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(userService.getAll(page, size)));
    }

    @GetMapping("/get/all-students-page")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getAllStudents(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(userService.getAllStudents(page, size)));
    }

    @GetMapping("/get/all-teachers-page")
    public ResponseEntity<ResponseDTO<Page<UserEntity>>> getAllTeachers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(userService.getAllTeachers(page, size)));
    }


}
