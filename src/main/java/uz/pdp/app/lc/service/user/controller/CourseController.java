package uz.pdp.app.lc.service.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app.lc.dto.CourseCreateDTO;
import uz.pdp.app.lc.dto.CourseUpdateDTO;
import uz.pdp.app.lc.dto.ResponseDTO;
import uz.pdp.app.lc.entity.CourseEntity;
import uz.pdp.app.lc.service.course.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<CourseEntity>> addCourse(@RequestBody CourseCreateDTO courseCreateDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(courseService.addCourse(courseCreateDTO)));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<CourseEntity>> updateCourse(@RequestBody CourseUpdateDTO courseUpdateDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(courseService.updateCourse(courseUpdateDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> addCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Success"));
    }

    @PutMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<CourseEntity>> updateCourse(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(courseService.GetById(id)));
    }

    @PutMapping("/get/all-courses")
    public ResponseEntity<ResponseDTO<List<CourseEntity>>> updateCourse() {
        List<CourseEntity> coursesList = courseService.getAll();
        return ResponseEntity.ok(new ResponseDTO<>(coursesList));
    }

}
