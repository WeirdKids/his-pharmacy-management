package com.pharmacy.management.controller;

import com.alibaba.fastjson.JSON;
import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.result.RepertoryResult;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RepertoryController {

    @Autowired
    DrugService drugService;

    // 解决跨域问题
    @CrossOrigin
    @PostMapping(value = "/api/query")
    @ResponseBody
    public RepertoryResult query(@RequestBody Drug requestDrug) {
        String mnemonicCode = requestDrug.getMnemonicCode();
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getByMnemonicCode("%"+ mnemonicCode +"%");
        if (drugs.size() == 0) {
            return new RepertoryResult(400, "没有匹配数据", null);
        } else {
            return new RepertoryResult(200, "操作执行成功", drugs);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/queryAll")
    @ResponseBody
    public RepertoryResult queryAll() {
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getAll();
        return new RepertoryResult(200, "操作执行成功", drugs);
    }

    @CrossOrigin
    @PostMapping(value = "/api/deleteRepertory")
    @ResponseBody
    public RepertoryResult deleteRepertory(@RequestBody Drug requestDrug) {
        int id = requestDrug.getId();
        String s = requestDrug.getMnemonicCode();
        drugService.deleteRepertory(id);
        List<Drug>  drugs = new ArrayList<>();
        if (s.isEmpty()) {
            drugs = drugService.getAll();
            return new RepertoryResult(200, "删除成功", drugs);
        } else {
            drugs = drugService.getByMnemonicCode(s);
            return new RepertoryResult(200, "删除成功", drugs);
        }
    }
}
