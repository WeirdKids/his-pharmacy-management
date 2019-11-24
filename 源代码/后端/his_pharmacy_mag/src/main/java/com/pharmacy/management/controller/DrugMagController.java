package com.pharmacy.management.controller;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.result.DrugMagResult;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐奥飞
 * @date 2019-11-8 8:43
 */

@Controller
public class DrugMagController {

    @Autowired
    DrugService drugService;
    // 解决跨域问题
    @CrossOrigin
    @PostMapping(value = "api/repertory/manage/queryAll")
    @ResponseBody
    public DrugMagResult queryAll () {
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getAll();
        return new DrugMagResult(200, "执行成功！", drugs);
    }
}
