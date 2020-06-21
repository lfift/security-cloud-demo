package com.ift.cloud.consumer.controller;

import com.ift.cloud.consumer.feign.ProviderServiceFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @since 2020/6/21
 */
@RestController
public class HelloController {

    private final ProviderServiceFeignClient providerServiceFeignClient;

    public HelloController(ProviderServiceFeignClient providerServiceFeignClient) {
        this.providerServiceFeignClient = providerServiceFeignClient;
    }

    @GetMapping("/hello")
    public String hello() {
        return providerServiceFeignClient.hello();
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "Admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "User";
    }
}
