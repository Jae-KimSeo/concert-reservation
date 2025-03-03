package com.project.concert_reservation.interfaces.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        logger.info("Request URI: {}", httpRequest.getRequestURI());
        logger.info("Request Method: {}", httpRequest.getMethod());
        logger.info("Request Headers:");
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("{}: {}", headerName, httpRequest.getHeader(headerName));
        }

        chain.doFilter(request, httpResponse);

        logger.info("Response Status: {}", httpResponse.getStatus());
        logger.info("Response Headers:");
        for (String headerName : httpResponse.getHeaderNames()) {
            logger.info("{}: {}", headerName, httpResponse.getHeader(headerName));
        }
    }

    @Override
    public void destroy() {
        // 필터 제거
    }
}
