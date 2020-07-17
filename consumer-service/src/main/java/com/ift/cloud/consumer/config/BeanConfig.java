package com.ift.cloud.consumer.config;

import com.ift.cloud.consumer.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 手动注入Bean配置
 *
 * @author liufei
 * @date 2020/7/17
 */
@Configuration
public class BeanConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
