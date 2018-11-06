package com.demo.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gold.li on 2018/7/14.
 */
@Controller
@RequestMapping("user")
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println(username +"=====" + password);
        return "login";
    }

}
