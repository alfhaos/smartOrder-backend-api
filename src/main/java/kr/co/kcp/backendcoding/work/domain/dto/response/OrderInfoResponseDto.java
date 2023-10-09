package kr.co.kcp.backendcoding.work.domain.dto.response;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.co.kcp.backendcoding.work.domain.vo.OrderMenusVo;
import kr.co.kcp.backendcoding.work.domain.vo.OrderPaymentsVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderInfoResponseDto {

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private int orderAmount;

    @Size(min = 1, max = 10)
    @NotNull
    @NotBlank
    private String orderDate;

    @NotNull
    private List<OrderMenusVo> orderMenus;

    @NotNull
    private List<OrderPaymentsVo> orderPayments;

}
