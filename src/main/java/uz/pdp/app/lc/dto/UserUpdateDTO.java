package uz.pdp.app.lc.dto;

public record UserUpdateDTO(String firstName, String lastName, String phoneNumber, String password, Integer age, Long userId) {
}
