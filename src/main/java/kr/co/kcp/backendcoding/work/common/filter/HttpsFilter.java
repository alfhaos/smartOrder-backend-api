package kr.co.kcp.backendcoding.work.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.kcp.backendcoding.work.common.config.RereadableRequestWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class HttpsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 인스턴스 초기화
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // requestUri 선언
        String requestURI = ((HttpServletRequest) request).getRequestURI();

        // h2-console에서의 요청일 경우 wrapper 및 doFilter 제외
        if(!requestURI.contains("/h2-console")){
            RereadableRequestWrapper rereadableRequestWrapper = new RereadableRequestWrapper((HttpServletRequest) request);
            chain.doFilter(rereadableRequestWrapper , response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 필터 인스턴스 종료
        Filter.super.destroy();
    }

}
