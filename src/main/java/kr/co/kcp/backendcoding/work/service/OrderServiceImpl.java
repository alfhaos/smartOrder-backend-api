package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.dto.request.OrderInfoRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.OrderInfoResponseDto;
import kr.co.kcp.backendcoding.work.domain.payment.Card;
import kr.co.kcp.backendcoding.work.domain.payment.Cash;
import kr.co.kcp.backendcoding.work.domain.payment.PaymentType;
import kr.co.kcp.backendcoding.work.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    @Override
    public CommonResponseDto orderInfo(OrderInfoRequestDto orderInfoRequest) {

        CommonResponseDto commonResponseDto = new CommonResponseDto();
        OrderInfoResponseDto orderInfoResponseDto = orderRepository.orderInfo(orderInfoRequest);

        commonResponseDto.setData(orderInfoResponseDto);

        if(orderInfoResponseDto == null){
            // 조회 결과가 null일 경우
            commonResponseDto.setCode(1);
            commonResponseDto.setMessage("조회된 주문 정보가 없습니다.");
        } else {
            // 조회 결과가 null이 아닐 경우
            commonResponseDto.setCode(0);
            commonResponseDto.setMessage("조회된 주문 정보 입니다.");
        }

        return commonResponseDto;
    }

    @Override
    @Transactional
    public CommonResponseDto orderReservation(OrderReservationRequestDto orderReservationRequestDto) throws Exception {

        String reqPaymentType = orderReservationRequestDto.getPaymentType();

        // 랜덤값 생성
        String reservationId = createReservationId();
        orderReservationRequestDto.setReservationId(reservationId);

        PaymentType paymentType = null;

        // 요청데이터의 paymentType에 따른 결제 타입 클래스 구현
        if(reqPaymentType.equals("CARD")){
            paymentType = new Card(reqPaymentType);
        } else if(reqPaymentType.equals("CASH")){
            paymentType = new Cash(reqPaymentType);
        } else {
            // CAHS, CARD가 아닐경우 에러 처리
            throw new IllegalArgumentException();
        }

        // 요청 Dto에 결제 타입 클래스 설정
        orderReservationRequestDto.setPayment(paymentType);
        // 결제 타입 클래스에 따른 결제 유형 반환 및 저장
        orderReservationRequestDto.setPaymentType(orderReservationRequestDto.processPayment());

        // 주문 예약 정보 저장
        paymentService.saveOrderReservation(orderReservationRequestDto);

        // 알림 발송 기능
        return paymentService.sendNotification(reservationId);
    }

    // 알파벳 + 숫자로 이루어진 reservationId 생성 함수
    public static String createReservationId() {

        // 알파벳 선언
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // 숫자 선언
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();

        // 최소 1개의 알파벳 추가
        sb.append(alphabet.charAt(new Random().nextInt(alphabet.length())));

        // 최소 1개의 숫자 추가
        sb.append(numbers.charAt(new Random().nextInt(numbers.length())));

        // 나머지 문자를 랜덤하게 추가하여 총 길이가 9가 될 때까지 반복
        while (sb.length() < 9) {
            int index = new Random().nextInt(alphabet.length() + numbers.length());
            if (index < alphabet.length()) {
                sb.append(alphabet.charAt(index));
            } else {
                sb.append(numbers.charAt(index - alphabet.length()));
            }
        }

        return sb.toString();
    }
}