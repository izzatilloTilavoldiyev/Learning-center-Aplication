package uz.pdp.app.lc.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GroupCreateDTO(
        @NotBlank String name,
        @NotNull @Column(name = "course_entity_id") Long courseId,
        @NotNull @Column(name = "user_entity_id") Long teacherId
) {
}
