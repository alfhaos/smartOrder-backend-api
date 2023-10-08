package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import kr.co.kcp.backendcoding.work.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
* 결제 공통 인터페이스 PaymentService을 구현한 impl 클래스
*
* */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final OrderRepository orderRepository;
    @Override
    public void saveOrderReservation(OrderReservationRequestDto orderReservationRequestDto) {
        orderRepository.saveOrderReservation(orderReservationRequestDto);
    }

    @Override
    public CommonResponseDto sendNotification(String reservationId) {
        return orderRepository.sendNotification(reservationId);
    }
}
