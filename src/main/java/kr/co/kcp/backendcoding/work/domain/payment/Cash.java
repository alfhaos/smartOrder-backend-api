package kr.co.kcp.backendcoding.work.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cash implements PaymentType{

    private String paymentType;

    // 결제 타입 로직
    @Override
    public String processPayment() {
        return "AA02";
    }
}
