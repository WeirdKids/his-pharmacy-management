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
    @PostMapping("/api/service/sendDrugs/send")
    @ResponseBody
    public SendDrugsResult send(@RequestBody Prescription pres){


        int num=pres.getNum();
       // System.out.println(num);
        String drugName=pres.getDrugName();
      //  System.out.println(drugName);
        int pres_id=pres.getId();
      //  System.out.println(pres_id);

        //通过处方编号查询处方并修改其状态
      //  System.out.println("正在查询处方单");
        Prescription Prescription=presService.getByPresId(pres_id);
        int sendNum=num/Prescription.getTotalStage(); //当前发送数量等于发送总数量除以疗程数
        if(Prescription.getStatue().equals("已发放")){
         //   System.out.println("情况1");
            return new SendDrugsResult(400, "当前订单已发送完毕",null);
        }
        else if(Prescription.getStatue().equals("缺货")){
          //  System.out.println("情况2");
            return new SendDrugsResult(401, "当前药品库存不足",null);
        }
        else if(Prescription.getTotalStage()-Prescription.getCurrentStage()==1) {
           // System.out.println("情况3");
            Prescription.setStatue("已发放"); //疗程结束，修改处方状态
            Prescription.setCurrentStage(Prescription.getTotalStage());//增加当前疗程
            Prescription.setSentNum(Prescription.getSentNum()+sendNum);//修改已发药的数量
        }
        else{
          //  System.out.println("情况4");
            Prescription.setStatue("暂存"); //疗程开始，修改处方状态
            Prescription.setCurrentStage(Prescription.getCurrentStage()+1);//增加当前疗程
            Prescription.setSentNum(Prescription.getSentNum()+sendNum);//修改已发药的数量
        }

        //通过药品名称查询药品并修改其数量
      //  System.out.println("正在查询药品");
        Drug drug= drugService.getByDrugsName(drugName);
        List<Warehouse> warehouses=drug.getWarehouses();
        if(warehouses.get(0).getNum()<num)//配药房中数量不足
            return new SendDrugsResult(402,"库存不足！请先使用库存管理从储存处调取药品",null);
        else if (drug.getTotalNum()<num)//总表中数量不足
            return new SendDrugsResult(401, "当前药品库存不足",null);
        else {
            warehouses.get(0).setNum(warehouses.get(0).getNum() - sendNum); //修改配药房中数量
            drug.setTotalNum(drug.getTotalNum() - sendNum);//修改总表中数量
        }
        drugService.Update(drug);
        presService.Update(Prescription);
        List<Prescription> prescriptions = presService.getAll();
        return new SendDrugsResult(200,"发药成功",prescriptions);
    }
}
