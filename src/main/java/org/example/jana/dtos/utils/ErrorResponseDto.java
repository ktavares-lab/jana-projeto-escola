package org.example.jana.dtos.utils;

public record ErrorResponseDto(String error, String timestamp, int codeHttp) {
}
