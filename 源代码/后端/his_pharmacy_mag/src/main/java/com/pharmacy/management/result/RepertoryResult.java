package com.pharmacy.management.result;

import com.pharmacy.management.bean.Repertory;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:30
 */

public class RepertoryResult {
    // 响应码
    private int code;
    // 反馈信息
    private String message;

    private List<?> repertories;

    public RepertoryResult(int code, String message, List<?> repertories) {
        this.code = code;
        this.message = message;
        this.repertories = repertories;
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

    public List<?> getRepertories() {
        return repertories;
    }

    public void setRepertories(List<?> repertories) {
        this.repertories = repertories;
    }
}
