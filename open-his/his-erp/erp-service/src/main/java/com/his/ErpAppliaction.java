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
public class ErpAppliaction {


    public static void main(String[] args) {

        SpringApplication.run(ErpAppliaction.class,args);
        System.out.println("ERP子项目启动成功");
    }

}
