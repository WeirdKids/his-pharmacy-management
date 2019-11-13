package com.pharmacy.management.result;
/**
 * @author 徐奥飞
 * @date 2019-11-5 8:30
 */
public class LoginResult {

    // 响应码
    private int code;

    private String message;

    public LoginResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public LoginResult(int code) {
        this.code = code;
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

}
