package com.example.casestudy.security;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TokenInfo {

    private final String accessToken;
}