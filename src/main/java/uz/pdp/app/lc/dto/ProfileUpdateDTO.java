package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotNull;

public record ProfileUpdateDTO(String firstName, String lastName, String password, Integer age, String bio,
                               @NotNull(message = "Id must not be null") Long id) {
}
