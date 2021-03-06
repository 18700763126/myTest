package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.UserMapper;
import com.his.domain.User;
import com.his.service.UserService;
/**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private  UserMapper userMapper;

    @Override
    public User getPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.COL_PHONE,phone);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User getUserId(Long userId) {
        return this.userMapper.selectById(userId);
    }
}
