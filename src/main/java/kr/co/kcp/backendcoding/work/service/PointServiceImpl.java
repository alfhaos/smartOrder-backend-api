package kr.co.kcp.backendcoding.work.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.kcp.backendcoding.work.domain.dto.request.PointSearchRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.ApiResponseDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class PointServiceImpl implements PointService{

    @Value("${point.uri}")
    private String uri;
    @Value("${point.max-count}")
    private int maxCount;

    private final RestTemplate restTemplate;

    @Override
    public CommonResponseDto pointSearch(PointSearchRequestDto pointSearchRequestDto) {
        // ObjectMapper 초기화
        ObjectMapper objectMapper = new ObjectMapper();

        // 재시도 횟수
        int retryCount = 0;
        // 요청 타입 추출
        String type = extractionType(pointSearchRequestDto.getPointType());

        // 요청 파라미터 추가
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(uri)
                .queryParam("type", type);

        while(retryCount < maxCount) {
            try {

                ApiResponseDto result = restTemplate.getForObject(uriBuilder.toUriString(), ApiResponseDto.class);

                return CommonResponseDto.builder()
                        .code(result.getStatus())
                        .message(result.getMessage())
                        .data(result.getResult())
                        .build();

            } catch (ResourceAccessException e) {
                retryCount++;
                if(retryCount < maxCount) {
                    // 재시도
                    ApiResponseDto result = restTemplate.getForObject(uriBuilder.toUriString(), ApiResponseDto.class);
                } else {
                    // 재시도 횟수 초과
                    return CommonResponseDto.builder()
                            .code(1)
                            .message("API 호출 실패 하였습니다.")
                            .build();
                }
            }
        }
        
        // 위의 try에서 return 되지 않았다면 실패 응답 리턴
        return CommonResponseDto.builder()
                .code(1)
                .message("API 호출 실패 하였습니다.")
                .build();
    }

    // 요청 타입 추출 함수
    public String extractionType(String code) {

        String[] arr = code.split("_");

        return arr[1];
    }

}
