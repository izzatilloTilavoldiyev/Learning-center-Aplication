package uz.pdp.app.lc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app.lc.dto.AuthenticationDTO;
import uz.pdp.app.lc.dto.AuthenticationRequest;
import uz.pdp.app.lc.dto.UserCreateDTO;
import uz.pdp.app.lc.service.authentication.AuthenticationService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationDTO> register(
            @RequestBody UserCreateDTO userCreateDTO
    ) {
        AuthenticationDTO register = authenticationService.register(userCreateDTO);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationDTO> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationDTO authenticate = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticate);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<AuthenticationDTO> refreshToken(
            Principal principal
    ) {
        AuthenticationDTO newAccessToken = authenticationService.getNewAccessToken(principal);
        return ResponseEntity.ok(newAccessToken);
    }
}
