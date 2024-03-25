package com.javaspringjwt.javaspringjwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDto {

    private String accessToken;
    private UserDto userDto;
}
