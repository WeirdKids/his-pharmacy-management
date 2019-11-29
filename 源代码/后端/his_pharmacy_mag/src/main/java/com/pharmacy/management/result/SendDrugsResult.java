package com.pharmacy.management.result;

import com.pharmacy.management.bean.Drug;

import java.util.List;

public class SendDrugsResult {

    private int code;
    // 反馈信息
    private String message;

    public SendDrugsResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
