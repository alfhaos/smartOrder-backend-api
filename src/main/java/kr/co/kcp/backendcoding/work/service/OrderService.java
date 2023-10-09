package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.dto.request.OrderInfoRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;

public interface OrderService {

    // 문제 2: 주문 정보 조회
    CommonResponseDto orderInfo(OrderInfoRequestDto orderInfoRequest);

    // 문제 3: 주문 예약 정보 저장
    CommonResponseDto orderReservation(OrderReservationRequestDto orderReservationRequestDto) throws Exception;
}
