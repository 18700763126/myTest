package com.his;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author liyan
 * @Date 2021-01-31
 * @Version ： V1.0
 * @Description:
 */
@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = {"com.his.mapper"})
public class SystemApplication {


    public static void main(String[] args) {

        SpringApplication.run(SystemApplication.class, args);
        System.out.println("主系统启动成功");

    }


}
