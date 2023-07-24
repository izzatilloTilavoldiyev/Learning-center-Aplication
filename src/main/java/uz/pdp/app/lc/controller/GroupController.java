package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app.lc.dto.GroupCreateDTO;
import uz.pdp.app.lc.dto.GroupUpdateDTO;
import uz.pdp.app.lc.dto.ResponseDTO;
import uz.pdp.app.lc.entity.GroupEntity;
import uz.pdp.app.lc.service.group.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<GroupEntity>> addGroup(
            @Valid @RequestBody GroupCreateDTO groupCreateDTO
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.addGroup(groupCreateDTO)));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<GroupEntity>> updateGroup(
            @RequestBody GroupUpdateDTO groupUpdateDTO
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.updateGroup(groupUpdateDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteGroup(@PathVariable Long id) {
        groupService.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<GroupEntity>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.getById(id)));
    }

    @GetMapping("/get/all")
    public ResponseEntity<ResponseDTO<List<GroupEntity>>> getAll() {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.getAll()));
    }

    @GetMapping("/get-by-course-id/{id}")
    public ResponseEntity<ResponseDTO<List<GroupEntity>>> getByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.getGroupByCourseId(id)));
    }

    @GetMapping("/get-by-teacher-id/{id}")
    public ResponseEntity<ResponseDTO<List<GroupEntity>>> getByTeacherId(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.getGroupByTeacherId(id)));
    }

    @PostMapping("/add-student")
    public ResponseEntity<ResponseDTO<GroupEntity>> addStudent(Long groupId, Long studentId) {
        GroupEntity groupEntity = groupService.addStudent(groupId, studentId);
        return ResponseEntity.ok(new ResponseDTO<>(groupEntity));
    }
}
