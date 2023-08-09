package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(String firstName, String lastName, String phoneNumber, String password, Integer age,
                            @NotNull(message = "Id must not be null") Long userId) {
}
