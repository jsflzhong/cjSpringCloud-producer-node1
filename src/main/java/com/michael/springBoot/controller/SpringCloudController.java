package com.michael.springBoot.controller;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(SpringCloudController.class);

    //应用管理bean
    @Autowired
    ApplicationInfoManager applicationInfoManager;

    /**
     * 测试通过eureka服务注册中心,来提供远程服务.
     * @param name 用户名 随意写
     * @return String
     * @author cj
     */
    @RequestMapping("/testHello")
    @ResponseBody
    public Object testHello(String name) throws InterruptedException {
        logger.info("@@@This is the service-provider-node1,the requesting name is : {}",name);
        //Test if the service is still running until finish all the request accepted after shut down by spring-cloud-admin.
        logger.info("@@@The service provider node1 is sleeping for 120 sec...");
        //Thread.sleep(120000);
        logger.info("@@@The service provider node1 wake up and keep running to finish the last request.");
        return "hello " + name + ",@@@this is testHello handler in ServiceProvider-node1";
    }

    /**
     * 手动修改服务的状态,实现控制服务的上下线.
     * 注意: 这种方式不健全,因为只改了服务自己这里的状态,并未把新状态注册到eureka.
     * 完善的版本见:ServiceRegistryController
     * @param status 目标状态.up; down; starting; outOfService;unknown;
     * @return
     */
    @RequestMapping("/changeServiceStatus")
    @ResponseBody
    public Object changeServiceStatus(String status) {
        InstanceInfo.InstanceStatus instanceStatus = null;
        switch (status){
            case "up":
                instanceStatus = InstanceInfo.InstanceStatus.UP;
                break;
            case "down":
                instanceStatus = InstanceInfo.InstanceStatus.DOWN;
                break;
            case "starting":
                instanceStatus = InstanceInfo.InstanceStatus.STARTING;
                break;
            case "outOfService":
                instanceStatus = InstanceInfo.InstanceStatus.OUT_OF_SERVICE;
                break;
            case "unknown":
                instanceStatus = InstanceInfo.InstanceStatus.UNKNOWN;
                break;
            default:
                return "请求参数错误";
        }
        applicationInfoManager.getInfo().setStatus(instanceStatus);
        InstanceInfo.InstanceStatus currentStatus = applicationInfoManager.getInfo().getStatus();
        return "修改成功,当前状态为:" + currentStatus;
    }
}
