package com.sparta.springauth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j(topic = "LoggingFilter")
//@Component
@Order(1) // 순서를 지정할 때 사용
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // FilterChain : Filter를 이동할 때 사용하기 위해 사용
        // 전처리
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI(); // url 정보를 가지고 올 수 있음
        log.info(url); // error 표시하고 싶으면 log.error

        chain.doFilter(request, response); // 다음 Filter 로 이동

        // 후처리
        log.info("비즈니스 로직 완료");
    }
}

// 어떤 요청인지 log 찍음 --> doFilter를 통해 다음 Filter로 이동
// --> 내부 Filter --> DispatcherServlet --> Filter 단으로 요청을 응답(보냄)