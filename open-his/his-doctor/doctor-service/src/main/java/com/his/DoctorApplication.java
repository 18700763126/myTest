package com.his;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author liyan
 * @Date 2021-02-08
 * @Version ： V1.0
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.his.mapper")
public class DoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorApplication.class,args);
        System.out.println("就诊子系统启动成功");
    }

}
