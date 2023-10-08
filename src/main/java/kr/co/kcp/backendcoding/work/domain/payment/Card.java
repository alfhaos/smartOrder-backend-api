package kr.co.kcp.backendcoding.work.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card implements PaymentType{

    private String payMentType;

    // 결제 타입 로직
    @Override
    public String processPayment() {
        return "AA01";
    }
}
