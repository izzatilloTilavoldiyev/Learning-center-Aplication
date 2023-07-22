package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(@NotBlank String fullName, @NotBlank String phoneNumber) {
}
