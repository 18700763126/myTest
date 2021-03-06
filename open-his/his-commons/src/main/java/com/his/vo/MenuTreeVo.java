package com.his.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author liyan
 * @Date 2021-02-08
 * @Version ： V1.0
 * @Description: 构造菜单返回给前台的vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuTreeVo {

    private String id;

    private String serPath;

    private boolean show = true;

    public MenuTreeVo(String id, String serPath) {
        this.id = id;
        this.serPath = serPath;
    }
}
