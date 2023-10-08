package kr.co.kcp.backendcoding.work.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PointSearchRequestDto {

    @Size(min = 1, max = 6)
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[A-Za-z]{4}_[A-Za-z]$", message = "포인트 요청 값은 CODE_A~Z 입니다.")
    private String pointType;
}
