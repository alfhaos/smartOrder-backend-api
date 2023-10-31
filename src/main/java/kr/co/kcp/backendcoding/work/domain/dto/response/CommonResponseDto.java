package kr.co.kcp.backendcoding.work.domain.dto.response;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
