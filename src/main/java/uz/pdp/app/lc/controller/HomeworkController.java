package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app.lc.dto.HomeworkCreateDTO;
import uz.pdp.app.lc.dto.HomeworkUpdateDTO;
import uz.pdp.app.lc.dto.ResponseDTO;
import uz.pdp.app.lc.entity.HomeworkEntity;
import uz.pdp.app.lc.service.homework.HomeworkService;

@RestController
@RequestMapping("/api/v1/homework")
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;

    /**
     * ---add
     * ---get by id
     * ---get all
     * ---get deleted
     * ---get by group id
     * ---get by teacher id
     * update
     * delete
     */

    @PreAuthorize(value = "hasAnyRole({'MANAGER', 'TEACHER'})")
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<HomeworkEntity>> addHomework(
            @Valid @RequestBody HomeworkCreateDTO homeworkCreateDTO)
    {
        HomeworkEntity homeworkEntity = homeworkService.addHomework(homeworkCreateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(homeworkEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<HomeworkEntity>> getById(@PathVariable Long id) {
        HomeworkEntity homeworkEntity = homeworkService.getById(id);
        return ResponseEntity.ok(new ResponseDTO<>(homeworkEntity));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<Page<HomeworkEntity>>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Page<HomeworkEntity> allHomeworks = homeworkService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allHomeworks));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/all-deleted")
    public ResponseEntity<ResponseDTO<Page<HomeworkEntity>>> getAllDeleted(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Page<HomeworkEntity> allHomeworks = homeworkService.getAllDeleted(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(allHomeworks));
    }

    @GetMapping("/get-by-group-id/{id}")
    public ResponseEntity<ResponseDTO<Page<HomeworkEntity>>> getByGroupId(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @PathVariable Long id) {
        Page<HomeworkEntity> byGroupId = homeworkService.getByGroupId(page, size, id);
        return ResponseEntity.ok(new ResponseDTO<>(byGroupId));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @GetMapping("/get-by-created-by-id/{id}")
    public ResponseEntity<ResponseDTO<Page<HomeworkEntity>>> getByCreatedById(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @PathVariable Long id) {
        Page<HomeworkEntity> homeworkPages = homeworkService.getByCreatedById(page, size, id);
        return ResponseEntity.ok(new ResponseDTO<>(homeworkPages));
    }

    @PreAuthorize(value = "hasAnyRole({'MANAGER', 'TEACHER'})")
    @PutMapping()
    public ResponseEntity<ResponseDTO<HomeworkEntity>> updateHomework(
            @RequestBody HomeworkUpdateDTO homeworkUpdateDTO
    ) {
        HomeworkEntity homeworkEntity = homeworkService.updateHomework(homeworkUpdateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(homeworkEntity));
    }

    @PreAuthorize(value = "hasAnyRole({'MANAGER', 'TEACHER'})")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteHomework(@PathVariable Long id) {
        homeworkService.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }
}
