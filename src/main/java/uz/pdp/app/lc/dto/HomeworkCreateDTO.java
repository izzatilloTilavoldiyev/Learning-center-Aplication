package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HomeworkCreateDTO(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Long groupId,

        @NotNull Long createdBy
) {
}
