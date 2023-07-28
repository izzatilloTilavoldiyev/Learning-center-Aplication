package uz.pdp.app.lc.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    private String accessToken;

    private String refreshToken;

}
