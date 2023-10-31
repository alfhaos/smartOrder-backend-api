package kr.co.kcp.backendcoding.work.domain.dto.request;

public record OrderInfoRequestDto(String orderId) {
    public OrderInfoRequestDto {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId type cannot be null or blank.");
        }
        if (orderId.length() < 1 || orderId.length() > 6) {
            throw new IllegalArgumentException("orderId type length should be between 1 and 6.");
        }
        if (!orderId.matches("^[0-9]*$")) {
            throw new IllegalArgumentException("숫자만 입력 가능 합니다..");
        }
    }
}
