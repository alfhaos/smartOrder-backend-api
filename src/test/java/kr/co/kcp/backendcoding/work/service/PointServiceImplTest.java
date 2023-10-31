package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.dto.request.PointSearchRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@RunWith(SpringRunner.class)
public class PointServiceImplTest {

    @Autowired
    private PointService pointService;

    // 포인트 조회 테스트
    @Test
    public void pointTest() {

        // given : 요청 파라미터 생성
        PointSearchRequestDto pointSearchRequestDto = new PointSearchRequestDto("CODE_A");

        // when : 조회 결과
        CommonResponseDto commonResponseDto = pointService.pointSearch(pointSearchRequestDto);

        // result 값을 map으로 변환
        Map<String, Integer> map = (Map<String, Integer>) commonResponseDto.getData();

        // then :
        // 1. 조회된 주문 금액 확인
        assertThat(map.get("orderAmt")).isEqualTo(10000);
        // 2. 조회된 잔여포인트 확인
        assertThat(map.get("myPoint")).isEqualTo(5000);
        // 3. 조회된 사용가능최소적립포인트 확인
        assertThat(map.get("useMinPoint")).isEqualTo(1000);
        // 3. 조회된 사용가능최대적립포인트 확인
        assertThat(map.get("useMaxPoint")).isEqualTo(50000);
        // 3. 조회된 적립포인트 사용 단위 확인
        assertThat(map.get("useUnitPoint")).isEqualTo(10);
    }

}