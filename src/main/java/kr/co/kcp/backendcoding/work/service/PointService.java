package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.dto.request.PointSearchRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;

public interface PointService {
    CommonResponseDto pointSearch(PointSearchRequestDto pointSearchRequestDto);
}
