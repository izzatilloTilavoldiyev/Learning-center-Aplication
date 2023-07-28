package uz.pdp.app.lc.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app.lc.config.jwt.JwtService;
import uz.pdp.app.lc.dto.AuthenticationDTO;
import uz.pdp.app.lc.dto.AuthenticationRequest;
import uz.pdp.app.lc.dto.UserCreateDTO;
import uz.pdp.app.lc.entity.UserEntity;
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
        UserEntity user = USER_MAPPER.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        userRepository.save(user);
        var accessToken = jwtService.generateAccessToken((UserDetails) user);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) user);
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
        var user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();
        var jwtToken = jwtService.generateAccessToken((UserDetails) user);
        return AuthenticationDTO.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationDTO getNewAccessToken(Principal principal) {
        UserEntity user = userRepository.findByPhoneNumber(principal.getName())
                .orElseThrow();
        String accessToken = jwtService.generateAccessToken((UserDetails) user);
        return AuthenticationDTO.builder().accessToken(accessToken).build();
    }
}
