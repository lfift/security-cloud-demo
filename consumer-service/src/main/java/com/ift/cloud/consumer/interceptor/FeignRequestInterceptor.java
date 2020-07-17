package com.ift.cloud.consumer.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * FeignClient远程调用拦截器
 *
 * @author liufei
 * @date 2020/7/17
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    private static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String xAuthToken = request.getHeader(HEADER_X_AUTH_TOKEN);
        requestTemplate.header(HEADER_X_AUTH_TOKEN, xAuthToken);
    }
}
