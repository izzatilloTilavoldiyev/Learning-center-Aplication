package uz.pdp.app.lc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<HomeworkEntity>> addHomework(
            @Valid @RequestBody HomeworkCreateDTO homeworkCreateDTO)
    {
        HomeworkEntity homeworkEntity = homeworkService.addHomework(homeworkCreateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(homeworkEntity));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<HomeworkEntity>> updateHomework(
            @RequestBody HomeworkUpdateDTO homeworkUpdateDTO
    ) {
        HomeworkEntity homeworkEntity = homeworkService.updateHomework(homeworkUpdateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(homeworkEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteHomework(@PathVariable Long id) {
        homeworkService.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<HomeworkEntity>> getById(@PathVariable Long id) {
        HomeworkEntity homeworkEntity = homeworkService.getById(id);
        return ResponseEntity.ok(new ResponseDTO<>(homeworkEntity));
    }

    @GetMapping("/get-by-group-id/{id}")
    public ResponseEntity<ResponseDTO<Page<HomeworkEntity>>> getByGroupId(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @PathVariable Long id) {
        Page<HomeworkEntity> byGroupId = homeworkService.getByGroupId(page, size, id);
        return ResponseEntity.ok(new ResponseDTO<>(byGroupId));
    }

    @GetMapping("/get/all")
    public ResponseEntity<ResponseDTO<Page<HomeworkEntity>>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Page<HomeworkEntity> all = homeworkService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(all));
    }
}
