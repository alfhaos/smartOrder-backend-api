package kr.co.kcp.backendcoding.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderInfoRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.PointSearchRequestDto;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanValidationTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    // 문제 2 request 요청 파라미터 유효성 검사
    @Test
    public void beanValidationTwo() {

        // given : 요청 파라미터 Dto 객체 생성
        OrderInfoRequestDto orderInfoRequest = OrderInfoRequestDto.builder()
                .orderId("aasdd").build();

        // when : 유효성 검사
        Set<ConstraintViolation<OrderInfoRequestDto>> violations = validator.validate(orderInfoRequest);

        // then : 유효성 검사 체크 결과
        assertThat(violations).isEmpty();
        
    }

    // 문제 3: request 요청 파라미터 유효성 검사
    @Test
    public void beanValidationThree() {

        // given : 요청 파라미터 Dto 객체 생성
        OrderReservationRequestDto orderReservationRequestDto = OrderReservationRequestDto.builder()
                .paymentType("AA01")
                .shopCode("S0001")
                .orderAmount(20000)
                .discountAmount(2000)
                .paymentAmount(18000)
                .build();

        // when : 유효성 검사
        Set<ConstraintViolation<OrderReservationRequestDto>> violations = validator.validate(orderReservationRequestDto);

        // then : 유효성 검사 체크 결과
        assertThat(violations).isEmpty();
    }

    // 문제 4: request 요청 파라미터 유효성 검사
    @Test
    public void beanValidationFour() {

        // given : 요청 파라미터 Dto 객체 생성
        PointSearchRequestDto pointSearchRequestDto = PointSearchRequestDto.builder()
                .pointType("CODE_A")
                .build();

        // when : 유효성 검사
        Set<ConstraintViolation<PointSearchRequestDto>> violations = validator.validate(pointSearchRequestDto);

        // then : 유효성 검사 체크 결과
        assertThat(violations).isEmpty();
    }
}
