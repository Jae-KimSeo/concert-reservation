package com.project.concert_reservation.common.filter;

import jakarta.servlet.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Component
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
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

        ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper);

        logger.info("Response Status: {}", httpResponse.getStatus());
        logger.info("Response Headers:");
        for (String headerName : httpResponse.getHeaderNames()) {
            logger.info("{}: {}", headerName, httpResponse.getHeader(headerName));
        }
        logger.info("Response Body: {}", responseWrapper.getContent());
    }

    @Override
    public void destroy() {
        // 필터 제거
    }
}

class ResponseWrapper extends HttpServletResponseWrapper {

    private StringWriter contentWriter = new StringWriter();

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(contentWriter);
    }

    public String getContent() {
        return contentWriter.toString();
    }
}
