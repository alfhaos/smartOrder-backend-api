package kr.co.kcp.backendcoding.work.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderInfoRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.OrderInfoResponseDto;
import kr.co.kcp.backendcoding.work.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/info")
    public CommonResponseDto orderInfo(@Validated @ModelAttribute OrderInfoRequestDto orderInfoRequest) throws Exception {

        return orderService.orderInfo(orderInfoRequest);
    }

    @PostMapping("/reservation")
    public CommonResponseDto orderReservation(@Validated @RequestBody OrderReservationRequestDto orderReservationRequestDto) throws Exception {

        return orderService.orderReservation(orderReservationRequestDto);
    }

}
