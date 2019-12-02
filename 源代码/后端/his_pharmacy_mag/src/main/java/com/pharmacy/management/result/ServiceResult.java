package com.pharmacy.management.result;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Prescription;

import java.util.List;

public class ServiceResult {

    private int code;
    // 反馈信息
    private String message;
    //处方单对象
    private List<Prescription> prescriptions;

    public ServiceResult(int code, String message, List<Prescription> prescriptions) {
        this.code = code;
        this.message = message;
        this.prescriptions=prescriptions;
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

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
