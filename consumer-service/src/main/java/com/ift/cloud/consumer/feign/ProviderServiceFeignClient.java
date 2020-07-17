package com.ift.cloud.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liufei
 * @since 2020/6/21
 */
@FeignClient(value = "provider-service", url = "${service.provider-service.url:}")
public interface ProviderServiceFeignClient {

    @GetMapping("/hello")
    String hello();
}
