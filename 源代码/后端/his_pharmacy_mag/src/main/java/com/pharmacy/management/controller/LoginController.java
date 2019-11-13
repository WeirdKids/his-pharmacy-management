package com.pharmacy.management.controller;

import com.pharmacy.management.bean.User;
import com.pharmacy.management.result.LoginResult;
import com.pharmacy.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

/**
 * @author 徐奥飞
 * @date 2019-11-5 8:43
 */

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin // 解决跨域问题
    @PostMapping(value = "/api/login")
    @ResponseBody
    public LoginResult login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.getByName(username);
        if (user == null) {
            return new LoginResult(400, "此用户名不存在！");
        } else if (user.getPassword().equals(requestUser.getPassword())) {
            return new LoginResult(200);
        } else {
            return new LoginResult(400, "密码错误！");
        }
    }

}

