package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;

/*
* 결제 공통 기능을 담고있는 인터페이스
*
* */
public interface PaymentService {

    // 주문 에약 정보 저장
    void saveOrderReservation(OrderReservationRequestDto orderReservationRequestDto) throws Exception;

    // 알림 발송
    CommonResponseDto sendNotification(String reservationId);
}
