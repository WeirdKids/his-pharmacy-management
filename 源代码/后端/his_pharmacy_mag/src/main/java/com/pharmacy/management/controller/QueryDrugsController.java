package com.pharmacy.management.controller;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.result.QueryDrugsResult;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QueryDrugsController {
    @Autowired
    DrugService drugService;

    @CrossOrigin
    @PostMapping(value= "/api/query/drugs_query/query")
    @ResponseBody
    public QueryDrugsResult query(@RequestBody Drug requestDrugs){
        String mnemonicCode = requestDrugs.getMnemonicCode();
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getByMnemonicCodeLike("%" + mnemonicCode + "%");
        if (drugs.size() == 0) {
            return new QueryDrugsResult(400, "没有匹配数据", null);
        } else {
            return new QueryDrugsResult(200, "操作执行成功", drugs);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/query/drugs_query/queryAll")
    @ResponseBody
    public QueryDrugsResult queryAll() {
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getAll();
        return new QueryDrugsResult(200, "操作执行成功", drugs);
    }
}
