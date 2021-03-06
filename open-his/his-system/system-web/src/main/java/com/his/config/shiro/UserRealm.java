package com.his.config.shiro;

import com.his.domain.User;
import com.his.service.UserService;
import com.his.vo.ActiverUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */
public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;

//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String s = authenticationToken.getPrincipal().toString();
        User user = userService.getPhone(s);
        //组装存放放到redis里面的对象
        ActiverUser activerUser = new ActiverUser();
        activerUser.setUser(user);
        if (!ObjectUtils.isEmpty(user)){ //说明用户存在,但是密码未必是正确的
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                    activerUser,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName()
            );
            return  info;
        }else {
            return null ; //用户不存在
        }
    }

//授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActiverUser user =(ActiverUser)principalCollection.getPrimaryPrincipal(); // 身份得到的是上一个方法（认证）返回值第一个参数activerUser
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }
}
