package com.example.springSecurity6.dto;


import lombok.Data;


@Data

public record AuthResponseDto(String accessToken) {
    public void setAccessToken(String token) {
    }
}
