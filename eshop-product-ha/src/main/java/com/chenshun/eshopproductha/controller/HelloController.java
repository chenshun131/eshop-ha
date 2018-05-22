package com.chenshun.eshopproductha.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: mew <p />
 * Time: 18/5/21 14:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello(String name) {
        return "hello," + name;
    }

}
