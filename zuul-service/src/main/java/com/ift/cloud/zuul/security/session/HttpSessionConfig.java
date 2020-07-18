package com.ift.cloud.zuul.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

/**
 * HttpSession配置
 *
 * @author liufei
 * @date 2020/6/21
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    /**
     * Session策略
     *
     * @return 使用Header传递Session的策略
     */
    @Bean
    public HeaderHttpSessionIdResolver headerHttpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    /**
     * SpringSession的Redis序列化策略
     *
     * @return 序列化策略
     */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModules(new WebServletJackson2Module());
//        objectMapper.registerModules(new WebJackson2Module());
        objectMapper.registerModule(new CoreJackson2Module());
        SecurityJackson2Modules.enableDefaultTyping(objectMapper);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }
}
