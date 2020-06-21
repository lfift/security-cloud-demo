package com.ift.cloud.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @since 0.0.1
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return  "Hello";
    }
}
