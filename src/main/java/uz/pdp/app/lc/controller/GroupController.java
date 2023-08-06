package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * ---add
     * ---add student to group
     * get by id
     * get all
     * get teacher's groups
     * get student's groups
     * get course's groups
     * update
     * update teacher
     * delete
     * delete student
     */

    @PreAuthorize(value = "hasRole('MANAGER')")
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<GroupEntity>> addGroup(
            @Valid @RequestBody GroupCreateDTO groupCreateDTO
    ) {
        GroupEntity groupEntity = groupService.addGroup(groupCreateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(groupEntity));
    }

    @PreAuthorize(value = "hasAnyRole({'MANAGER', 'TEACHER'})")
    @PostMapping("/add-student")
    public ResponseEntity<ResponseDTO<String>> addStudent(
            @RequestParam Long groupId,
            @RequestParam Long studentId
    ) {
        groupService.addStudent(groupId, studentId);
        return ResponseEntity.ok(new ResponseDTO<>("Successfully added"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<GroupEntity>> getById(@PathVariable Long id) {
        GroupEntity groupEntity = groupService.getById(id);
        return ResponseEntity.ok(new ResponseDTO<>(groupEntity));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<GroupEntity>> updateGroup(
            @RequestBody GroupUpdateDTO groupUpdateDTO
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.updateGroup(groupUpdateDTO)));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteGroup(@PathVariable Long id) {
        groupService.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/get/all")
    public ResponseEntity<ResponseDTO<List<GroupEntity>>> getAll() {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.getAll()));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/get-by-course-id/{id}")
    public ResponseEntity<ResponseDTO<List<GroupEntity>>> getByCourseId(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.getGroupByCourseId(id)));
    }

    @PreAuthorize(value = "hasRole({'MANAGER', 'TEACHER'})")
    @GetMapping("/get-by-teacher-id/{id}")
    public ResponseEntity<ResponseDTO<List<GroupEntity>>> getByTeacherId(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(groupService.getGroupByTeacherId(id)));
    }
}
