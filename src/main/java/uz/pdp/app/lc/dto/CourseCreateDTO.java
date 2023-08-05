package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
