package com.his.controller.system;

import com.his.constants.Constants;
import com.his.constants.HttpStatus;
import com.his.domain.Menu;
import com.his.domain.SimpleUser;
import com.his.dto.LoginBodyDto;
import com.his.service.MenuService;
import com.his.vo.ActiverUser;
import com.his.vo.AjaxResult;
import com.his.vo.MenuTreeVo;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */
@Log4j2
@RestController
public class LoginController {

    @Autowired
    private MenuService menuService;

    /**
     * 登录方法
     *
     * @return 结果
     */
    @PostMapping("login/doLogin")
    public AjaxResult login(@RequestBody @Validated LoginBodyDto loginBodyDto, HttpServletRequest request) {
        AjaxResult ajax = AjaxResult.success();
        String username = loginBodyDto.getUsername();
        String password = loginBodyDto.getPassword();
        //构造用户名和密码的token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            //得到会话的token==也就是redis里面存的
            Serializable webToken = subject.getSession().getId();
            ajax.put(Constants.TOKEN, webToken);
        } catch (Exception e) {
            log.error("用户名或密码不正确", e);
            ajax = AjaxResult.error(HttpStatus.ERROR, "用户名或密码不正确");
        }
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("login/getInfo")
    public AjaxResult getInfo() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("username", activerUser.getUser().getUserName());
        ajax.put("picture", activerUser.getUser().getPicture());
        ajax.put("roles", activerUser.getRoles());
        ajax.put("permissions", activerUser.getPermissions());
        return ajax;
    }

    /**
     * 用户退出
     */
    @PostMapping("login/logout")
    public AjaxResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return AjaxResult.success("用户退出成功");
    }

    /**
     * 获取应该显示的菜单信息
     *
     * @return 菜单信息
     */
    @GetMapping("login/getMenus")
    public AjaxResult getMeuns() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        boolean isAdmin = activerUser.getUser().getUserType().equals(Constants.USER_ADMIN);
        SimpleUser simpleUser = null;
        if (!isAdmin) {
            simpleUser = new SimpleUser(activerUser.getUser().getUserId(), activerUser.getUser().getUserName());
        }
        List<Menu> menus = menuService.selectMenuTree(isAdmin, simpleUser);
        List<MenuTreeVo> menuVos = new ArrayList<>();
        for (Menu menu : menus) {
            menuVos.add(new MenuTreeVo(menu.getMenuId().toString(), menu.getPath()));
        }
        return AjaxResult.success(menuVos);
    }

}
