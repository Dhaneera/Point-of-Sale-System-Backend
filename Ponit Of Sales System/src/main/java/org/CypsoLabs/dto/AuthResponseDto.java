package org.CypsoLabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType="Bearer";

    public AuthResponseDto(String accessToken ,String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
