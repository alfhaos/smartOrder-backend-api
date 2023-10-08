package kr.co.kcp.backendcoding.work.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderInfoRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.PointSearchRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import kr.co.kcp.backendcoding.work.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/point")
public class PointController {
    private final PointService pointService;

    @GetMapping("/search")
    public CommonResponseDto pointSearch(@Valid @ModelAttribute PointSearchRequestDto pointSearchRequestDto) throws Exception {

        return pointService.pointSearch(pointSearchRequestDto);

    }
}
