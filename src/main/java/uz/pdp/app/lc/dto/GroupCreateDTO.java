package uz.pdp.app.lc.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GroupCreateDTO(
        @NotBlank String name,
        @NotNull Long courseId,
        @NotNull Long teacherId
) {
}
