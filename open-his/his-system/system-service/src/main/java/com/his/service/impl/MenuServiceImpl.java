package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.his.constants.Constants;
import com.his.domain.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.domain.Menu;
import com.his.mapper.MenuMapper;
import com.his.service.MenuService;

/**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuTree(Boolean isAdmin, SimpleUser simpleUser) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq(Menu.COL_STATUS, Constants.STATUS_TRUE);
        wrapper.in(Menu.COL_MENU_TYPE, Constants.MENU_TYPE_M, Constants.MENU_TYPE_C);
        wrapper.orderByAsc(Menu.COL_MENU_ID);

        if (isAdmin) {
            return menuMapper.selectList(wrapper);
        } else {
            return menuMapper.selectList(wrapper);
        }

    }
}
