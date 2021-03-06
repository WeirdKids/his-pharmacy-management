package com.pharmacy.management.controller;

import com.pharmacy.management.bean.PresInfo;
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
    @PostMapping(value = "/api/query/prescription_query/querySelected")
    @ResponseBody
    public PresQueryResult querySelected(@RequestBody PresInfo presInfo){
        List<Prescription> prescriptions = new ArrayList<>();
        for(int i=0;i<presInfo.getPresIds().length;i++){
            prescriptions.add(presService.getByPresId(presInfo.getPresIds()[i]));
        }
        return new PresQueryResult(200,"",prescriptions);
    }


    @CrossOrigin
    @PostMapping(value = "/api/query/prescription_query/query")
    @ResponseBody
    public PresQueryResult queryByCode(@RequestBody Prescription requestPrescription){
        int prescriptionCode = requestPrescription.getPrescriptionCode();
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions = presService.getByPresCode(prescriptionCode);
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

    @CrossOrigin
    @PostMapping(value = "api/query/prescription_query/querySent")
    @ResponseBody
    public PresQueryResult querySent(){
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions = presService.getSent();
        if(prescriptions.size() == 0)
            return new PresQueryResult(400, "当前还未发放药品", null);
        else return new PresQueryResult(200,"已显示发放过的处方", prescriptions);
    }

    @CrossOrigin
    @PostMapping(value = "api/query/prescription_query/queryNotSent")
    @ResponseBody
    public PresQueryResult queryNotSent(){
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions = presService.getNotSent();
        if(prescriptions.size() == 0)
            return new PresQueryResult(400, "当前处方订单已全部完成", null);
        else return new PresQueryResult(200,"已显示未发放处方",prescriptions);
    }
}
