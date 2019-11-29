package com.pharmacy.management.controller;

import com.pharmacy.management.bean.Prescription;
import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.result.PresQueryResult;
import com.pharmacy.management.service.PresService;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.lang.String;
import org.springframework.web.util.HtmlUtils;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PresQueryController {
    @Autowired
    PresService presService;

    @CrossOrigin
    @PostMapping(value = "/api/query/prescription_query/query")
    @ResponseBody
    public PresQueryResult query(@RequestBody Prescription requestPrescription){
        int PrescriptionCode = requestPrescription.getPrescriptionCode();
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions = presService.getByPresCode(PrescriptionCode);
        if (prescriptions.size() == 0) {
            return new PresQueryResult(400, "没有匹配数据", null);
        } else {
            return new PresQueryResult(200, "操作执行成功", prescriptions);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/query/prescription_query/queryAll")
    @ResponseBody
    public PresQueryResult queryAll() {
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions = presService.getAll();
        return new PresQueryResult(200, "操作执行成功", prescriptions);
    }
}
