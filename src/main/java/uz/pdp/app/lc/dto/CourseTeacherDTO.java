package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotNull;

public record CourseTeacherDTO(
        @NotNull
        Long courseID,
        @NotNull
        Long teacherID) {
}
