package com.zerobase.zerobasewebsample.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Hello filter : " + Thread.currentThread());
        // 외부 -> Filter (-> 처리 ->) Filter -> 외부
        chain.doFilter(request, response); // chain 으로 연결
        log.info("Bye filter : " + Thread.currentThread());
    }
}
