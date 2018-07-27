package com.michael.springBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cj
 * springCloud测试controller
 * 2018/3/28
 */
@Controller
public class SpringCloudController {

    /**
     * 测试通过eureka服务注册中心,来提供远程服务.
     * @param name 用户名 随意写
     * @return String
     * @author cj
     */
    @RequestMapping("/testHello")
    @ResponseBody
    public Object testHello(String name) {
        return "hello " + name + ",@@@this is testHello handler in ServiceProvider-node1";
    }
}
