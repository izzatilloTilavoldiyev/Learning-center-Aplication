package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String password,

        Integer age,

        String bio,

        String role
) {
}
