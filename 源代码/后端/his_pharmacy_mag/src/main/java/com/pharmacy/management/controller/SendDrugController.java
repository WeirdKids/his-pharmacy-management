package com.pharmacy.management.controller;
import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Prescription;
import com.pharmacy.management.bean.Warehouse;
import com.pharmacy.management.result.SendDrugsResult;
import com.pharmacy.management.service.DrugService;
import com.pharmacy.management.service.PresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SendDrugController {
    @Autowired
    DrugService drugService;
    @Autowired
    PresService presService;

    @CrossOrigin
    @PostMapping("/api/outpatient/SendDrugs/send")
    @ResponseBody
    public SendDrugsResult send(@RequestBody Prescription pres){

        int num=pres.getNum();
        String drugName=pres.getDrugName();
        int pres_id=pres.getId();


        //通过处方编号查询处方并修改其状态
        Prescription prescription=presService.getByPresId(pres_id);
        if(prescription.getStatue()=="已发放")
            return new SendDrugsResult(400, "当前订单已发送完毕");
        else if(prescription.getStatue()=="缺货")
            return new SendDrugsResult(401, "当前药品库存不足");
        else if(prescription.getTotalStage()-prescription.getCurrentStage()==1) {
            prescription.setStatue("已发放"); //疗程结束，修改处方状态
            prescription.setCurrentStage(prescription.getTotalStage());//增加当前疗程
        }
        else{
            prescription.setStatue("暂存"); //疗程开始，修改处方状态
            prescription.setCurrentStage(prescription.getCurrentStage()+1);//增加当前疗程
        }

        //通过药品名称查询药品并修改其数量
        Drug drug= drugService.getByDrugsName(drugName);
        List<Warehouse> warehouses=drug.getWarehouses();
        if(warehouses.get(0).getNum()<num)//配药房中数量不足
            return new SendDrugsResult(402,"库存不足！请先使用库存管理从储存处调取药品");
        else if (drug.getTotalNum()<num)//总表中数量不足
            return new SendDrugsResult(401, "当前药品库存不足");
        else {
            warehouses.get(0).setNum(warehouses.get(0).getNum() - num); //修改配药房中数量
            drug.setTotalNum(drug.getTotalNum() - num);//修改总表中数量
        }
        drugService.Update(drug);
        presService.Update(prescription);
        return new SendDrugsResult(200,"发药成功");
    }
}
