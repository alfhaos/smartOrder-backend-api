package kr.co.kcp.backendcoding.work.domain.dto.response;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApiResponseDto {

    @Digits(integer = 10, fraction = 0)
    @NotNull
    private int status;

    @Size(min = 1, max = 255)
    @NotNull
    private String message;

    private Object result;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getResult() {
        return result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
