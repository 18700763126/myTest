package com.his.service;

import com.his.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */

public interface UserService extends IService<User>{


        /**
         *
         * @param phone  用户电话号码
         * @return   查询用户是否存在
         */
    User getPhone(String phone);

        /**
         *
         * @param userId
         * @return 用户id
         */
    User getUserId(Long userId);


}
