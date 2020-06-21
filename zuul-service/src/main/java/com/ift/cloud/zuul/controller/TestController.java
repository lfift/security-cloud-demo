package com.ift.cloud.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试接口
 *
 * @author liufei
 * @date 2020/6/21
 */
@RestController
public class TestController {

    @GetMapping("/userinfo")
    public Map<String, Object> userinfo(HttpSession session) {
        Map<String, Object> results = new HashMap<>(2);
        results.put("sessionid", session.getId());
        results.put("name", session.getAttribute("username"));
        return results;
    }
}
