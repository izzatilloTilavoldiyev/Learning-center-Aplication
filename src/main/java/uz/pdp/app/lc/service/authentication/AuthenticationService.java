package uz.pdp.app.lc.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.config.jwt.JwtService;
import uz.pdp.app.lc.dto.AuthenticationDTO;
import uz.pdp.app.lc.dto.AuthenticationRequest;
import uz.pdp.app.lc.dto.UserCreateDTO;
import uz.pdp.app.lc.entity.UserEntity;
import uz.pdp.app.lc.exception.DataNotFoundException;
import uz.pdp.app.lc.exception.DuplicateValueException;
import uz.pdp.app.lc.repository.UserRepository;

import java.security.Principal;

import static uz.pdp.app.lc.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationDTO register(UserCreateDTO userDTO) {
        if (userRepository.existsByPhoneNumber(userDTO.phoneNumber()))
            throw new DuplicateValueException("Phone number already exists");

        var user = USER_MAPPER.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        userRepository.save(user);
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationDTO authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );
        UserEntity user = findByPhoneNumber(request.getPhoneNumber());
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationDTO getNewAccessToken(Principal principal) {
        UserEntity user = findByPhoneNumber(principal.getName());
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private UserEntity findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new DataNotFoundException("User not found with '" + phoneNumber + "' phone number")
        );
    }
}

