package kr.co.kcp.backendcoding.work.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.kcp.backendcoding.work.domain.dto.request.PointSearchRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;

public interface PointService {
    CommonResponseDto pointSearch(PointSearchRequestDto pointSearchRequestDto);
}
