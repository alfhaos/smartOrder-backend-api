package kr.co.kcp.backendcoding.work.common.exception.handler.advice;

import kr.co.kcp.backendcoding.work.common.exception.handler.model.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/*
* 모든 컨트롤러에서 발생되는 예외를 관리하기 위한 RestControllerAdvice 설정
*/
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> exceptionHandler(Exception e){

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        // Timestamp to String
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTimestamp);

        HttpStatus status = null;

        // 예외 클래스에 따라 상태 코드를 설정
        if (e instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else if(e instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        int statusCode = status.value();
        String message = "";

        // 클라이언트, 서버 에러 발생에 따른 에러 메세지 설정
        if(statusCode >= 400 && statusCode <= 499) {
            message = "잘못된 요청 입니다.";
        } else if(statusCode >= 500 && statusCode <= 599) {
            message = "오류가 발생했습니다 관리자에게 문의 해주세요.";
        }

        ErrorResult result = ErrorResult.builder()
                .timestamp(timeStamp)
                .status(status.value())
                .message(message)
                .build();

        //에러로그
        log.error("error", e);

        return new ResponseEntity<>(result, status);
    }

}
