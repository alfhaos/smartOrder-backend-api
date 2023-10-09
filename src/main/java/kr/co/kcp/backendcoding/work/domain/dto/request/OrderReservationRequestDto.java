package kr.co.kcp.backendcoding.work.domain.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.co.kcp.backendcoding.work.domain.payment.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderReservationRequestDto {

    @Size(min = 1, max = 4)
    @NotNull
    @NotBlank
    private String paymentType;

    @Size(min = 1, max = 10)
    @NotNull
    @NotBlank
    private String shopCode;

    @Digits(integer = 10, fraction = 0)
    @NotNull
    private int orderAmount;

    @Digits(integer = 10, fraction = 0)
    @NotNull
    private int discountAmount;

    @Digits(integer = 10, fraction = 0)
    @NotNull
    private int paymentAmount;

    private String reservationId;

    private PaymentType payment;

    public String processPayment(){
       return payment.processPayment();
    }
}
