package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.NotNull;

public record GroupUpdateDTO(String name, @NotNull Long id) {
}
