package kr.co.kcp.backendcoding.work.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.kcp.backendcoding.work.common.model.RequestEnum;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

// request에 따른 로그를 기록 하기 위한 Interceptor
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final String appJson = "application/json";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String remoteIp = request.getRemoteAddr();
        String method = "";
        String parameterJson = "";
        Map<String, Object> headersMap = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();

        if(handler instanceof HandlerMethod) {
            //호출할 컨트롤러 메서드 정보
            HandlerMethod hm = (HandlerMethod) handler;
            method = hm.getMethod().getName();
        }

        // headres을 map형태로 변환
        headersMap = namesConvert(request.getHeaderNames(), request, RequestEnum.HEADERS.name());

        if(headersMap.get("content-type") == null){
            // request의 파라미터를 map형태로 변환
            parameterMap = namesConvert(request.getParameterNames(), request,RequestEnum.PARAMETER.name());
            parameterJson= new JSONObject(parameterMap).toJSONString();

        } else if(headersMap.get("content-type").equals(appJson)){
            // application/json 요청의 경우, 요청 바디를 읽어올 수 있습니다.
            parameterJson = getRequestBody(request);
        }
        // Map -> JSON 변환
        JSONObject headersJson = new JSONObject(headersMap);

        // 문제 1-2 : request 요청에 대한 공통 로그
        log.info("Request : Remote IP: {}, Headers: {}, Method: {}, URI: {}, Parameter: {}", remoteIp, headersJson, method, requestURI, parameterJson);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if(ex != null){
            log.error("error", ex);
        }

    }

    // request을 통해 받은 Enumeration형태의 값을 map형태로 변환하여 반환 코드
    public Map<String, Object> namesConvert(Enumeration<String> names, HttpServletRequest request, String division){

        Map<String, Object> tempMap = new HashMap<>();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String val = division.equals(String.valueOf(RequestEnum.HEADERS)) ? request.getHeader(name) : request.getParameter(name);
            tempMap.put(name, val);
        }

        return tempMap;
    }
    // 문제 3번에서 json형태로 request을 받을때 log에 담기위한 코드
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = request.getReader();

        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

}
