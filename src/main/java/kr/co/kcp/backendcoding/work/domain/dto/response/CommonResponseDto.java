package kr.co.kcp.backendcoding.work.domain.dto.response;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CommonResponseDto {

    @Digits(integer = 4, fraction = 0)
    @NotNull
    private int code;

    @Size(min = 1, max = 255)
    @NotBlank
    @NotNull
    private String message;

    private Object data;

}
