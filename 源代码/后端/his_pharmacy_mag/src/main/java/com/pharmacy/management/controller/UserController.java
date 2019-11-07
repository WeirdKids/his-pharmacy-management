//package com.pharmacy.management.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import com.pharmacy.management.bean.User;
//import com.pharmacy.management.service.UserService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author: 徐奥飞
// * @date: 2019年11月4日 17:50
// * @version 1.0
// */
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    public Object loginUser(User user) {
//        Map<String,Object> result = new HashMap<String, Object>();
//        System.err.println(user);
//        if("admin".equals(user.getUsername()) && "123456".equals(user.getcPwd())) {
//            result.put("code", 200);
//            result.put("msg", "登录成功");
//            result.put("token", "adminxxxx");
//            return result;
//        }
//
//
//        result.put("code", 500);
//        result.put("msg", "登录失败");
//        return result;
//    }
//
//    @PostMapping("/submit")
//    public Object submitUser(User user) {
//
//        Map<String,Object> result = new HashMap<String, Object>();
//        if(StringUtils.isBlank(user.getcId())) {
//            user.setcId(UUID.randomUUID().toString().replaceAll("-", ""));
//            userService.save(user);
//        }else{
//            userService.update(user);
//        }
//
//
//        result.put("success", true);
//        result.put("msg", "登录成功");
//        result.put("token", "adminxxxx");
//        return result;
//    }
//    @PostMapping("/userlist")
//    public List<User> userList(String filter){
//        return userService.listUser(filter);
//    }
//
//    @PostMapping("/delete")
//    public Map<String, Object> delete(String userId){
//        Map<String,Object> result = new HashMap<String, Object>();
//        if(StringUtils.isNotBlank(userId)) {
//            userService.deleteByPrimarykey(userId);
//            result.put("success", true);
//        }else {
//            result.put("success", false);
//        }
//        return result;
//    }
//
//}