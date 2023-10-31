package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.dto.request.OrderInfoRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.OrderInfoResponseDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.OrderReservationResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    // 문제 2 : 주문정보 조회 테스트
    @Test
    @Rollback(value = true)
    @Transactional
    public void OrderInfoTest() {

        // given : 요청 파라미터 생성
        OrderInfoRequestDto orderInfoRequestDto = new OrderInfoRequestDto("000001");

        // when : 조회 결과
        CommonResponseDto commonResponseDto = orderService.orderInfo(orderInfoRequestDto);

        OrderInfoResponseDto orderInfoResponseDto = (OrderInfoResponseDto) commonResponseDto.getData();
        // then :
        // 1. 조회된 주문 금액을 통해서 확인
        assertThat(orderInfoResponseDto.getOrderAmount()).isEqualTo(17000);
        // 2. 조회된 주문 메뉴를 통해서 확인
        assertThat(orderInfoResponseDto.getOrderMenus().get(0).getProductName()).isEqualTo("후라이드치킨");
        // 3. 조회된 주문 결제 정보를 통해서 확인
        assertThat(orderInfoResponseDto.getOrderPayments().get(0).getPaymentId()).isEqualTo(1);
    }

    // 문제 3 : 주문 예약 정보 테스트
    @Test
    @Transactional
    @Rollback(value = true)
    public void OrderReservation() throws Exception {

        // given : 요청 파라미터 생성
        OrderReservationRequestDto orderReservationRequestDto = OrderReservationRequestDto.builder()
                .paymentType("CASH")
                .shopCode("S0001")
                .orderAmount(1000)
                .discountAmount(500)
                .paymentAmount(500)
                .build();

        // when : 조회 결과
        CommonResponseDto commonResponseDto = orderService.orderReservation(orderReservationRequestDto);

        // then : 응답 메세지 확인
        OrderReservationResponseDto orderReservationResponseDto = (OrderReservationResponseDto) commonResponseDto.getData();
        assertThat(orderReservationResponseDto).isNotNull();
    }
}