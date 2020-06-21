package com.ift.cloud.zuul.security.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

/**
 * HttpSession配置
 *
 * @author liufei
 * @date 2020/6/21
 */
@Configuration
@EnableJdbcHttpSession
public class HttpSessionConfig {

    @Bean
    public HeaderHttpSessionIdResolver headerHttpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }
}
