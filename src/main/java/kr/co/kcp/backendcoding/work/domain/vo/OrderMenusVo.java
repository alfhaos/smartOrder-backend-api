package kr.co.kcp.backendcoding.work.domain.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenusVo {

    @NotNull
    @Size(min = 1, max = 255)
    private String productName;

    @NotNull
    @Size(min = 1, max = 10)
    private int price;
}
