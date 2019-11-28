package com.pharmacy.management.result;

import com.pharmacy.management.bean.Drug;

import java.util.List;

public class QueryDrugsResult {

    // 响应码
    private int code;
    // 反馈信息
    private String message;
    // 返回 drugs 对象
    private List<Drug> drugs;

    public QueryDrugsResult(int code, String message, List<Drug> drugs) {
        this.code = code;
        this.message = message;
        this.drugs = drugs;
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

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
