package com.his.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author liyan
 * @Date 2021-02-08
 * @Version ： V1.0
 * @Description: 用户对象传输类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUser  implements Serializable {

    private Serializable userId;
    private String userName;

}
