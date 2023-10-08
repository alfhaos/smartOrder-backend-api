package kr.co.kcp.backendcoding.work.common.exception.handler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResult {

    private String timestamp;
    private int status;
    private String message;

}
