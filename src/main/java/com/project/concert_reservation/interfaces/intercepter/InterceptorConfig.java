package com.project.concert_reservation.interfaces.intercepter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final Interceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePatterns = new ArrayList<>();
        excludePatterns.add("api/v1/users/**");

        registry.addInterceptor(interceptor)
                .excludePathPatterns(excludePatterns); // 인터셉터를 제외할 경로
    }
}
