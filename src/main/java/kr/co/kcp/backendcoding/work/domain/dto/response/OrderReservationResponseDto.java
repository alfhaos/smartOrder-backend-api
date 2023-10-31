package kr.co.kcp.backendcoding.work.domain.dto.response;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
public class OrderReservationResponseDto {

    @Size(min = 1, max = 9)
    private String reservationId;

}
