package com.pharmacy.management.result;

import com.pharmacy.management.bean.User;

/**
 * author 徐奥飞
 * date 2019-11-5 8:30
 */
public class LoginResult {

    // 响应码
    private int code;
    // 反馈信息
    private String message;
    // 反馈对象
    private User user;

    public LoginResult(int code, String message, User user) {
        this.code = code;
        this.message = message;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
