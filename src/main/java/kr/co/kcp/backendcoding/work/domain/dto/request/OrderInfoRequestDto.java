package kr.co.kcp.backendcoding.work.domain.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderInfoRequestDto {

    @Size(min = 1, max = 6)
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력 가능 합니다.")
    @NotNull
    String orderId;

}