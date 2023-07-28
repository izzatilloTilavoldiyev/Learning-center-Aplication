package uz.pdp.app.lc.dto;

import jakarta.validation.constraints.Positive;

public record CourseUpdateDTO(String name, String description, @Positive  Long id) {
}
