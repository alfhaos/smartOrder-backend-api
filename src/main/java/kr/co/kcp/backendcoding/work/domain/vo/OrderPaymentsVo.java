package kr.co.kcp.backendcoding.work.domain.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaymentsVo {

    @NotNull
    @Size(min = 1, max = 10)
    private Long paymentId;

    @NotNull
    @Size(min = 1, max = 255)
    private String paymentMethod;

    @NotNull
    @Size(min = 1, max = 10)
    private String paymentDate;
}
