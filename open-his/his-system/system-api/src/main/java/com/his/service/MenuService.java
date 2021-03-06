package com.his.service;

import com.his.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.domain.SimpleUser;

import java.util.List;

/**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */

public interface MenuService extends IService<Menu>{


    /**
     *  查询是否超级管理员，是超级管理员展示所有菜单 ，若不是根据用户角色展示不同的菜单
     * @param isAdmin 是否超级管理员
     * @param simpleUser isAdmin = true simpleUser可以为空
     * @return
     */
     List<Menu> selectMenuTree(Boolean isAdmin, SimpleUser simpleUser);


}
