package kr.co.kcp.backendcoding.work.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PointSearchRequestDto(String pointType) {
    public PointSearchRequestDto {
        if (pointType == null || pointType.isBlank()) {
            throw new IllegalArgumentException("Point type cannot be null or blank.");
        }
        if (pointType.length() < 1 || pointType.length() > 6) {
            throw new IllegalArgumentException("Point type length should be between 1 and 6.");
        }
        if (!pointType.matches("^[A-Za-z]{4}_[A-Za-z]$")) {
            throw new IllegalArgumentException("Point type should match the pattern CODE_A~Z.");
        }
    }
}
