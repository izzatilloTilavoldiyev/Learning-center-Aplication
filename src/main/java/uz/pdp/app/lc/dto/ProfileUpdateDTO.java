package uz.pdp.app.lc.dto;

public record ProfileUpdateDTO(String firstName, String lastName, String password, Integer age, String bio, Long id) {
}
