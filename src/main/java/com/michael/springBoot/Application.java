package com.michael.springBoot;

import com.michael.springBoot.dao.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages="com.michael.springBoot.dao",markerInterface = BaseMapper.class)
//@EnableScheduling
//@EnableEurekaServer
@EnableDiscoveryClient //使项目具有了服务注册的功能,把本服务注册进入eureka注册中心.
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
