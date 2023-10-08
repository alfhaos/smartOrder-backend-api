package kr.co.kcp.backendcoding.work.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApiResponseDto {

    private int status;

    private String message;

    private Object result;

}
