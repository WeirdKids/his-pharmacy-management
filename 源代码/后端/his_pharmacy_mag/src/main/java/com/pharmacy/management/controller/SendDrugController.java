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

import java.util.ArrayList;
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
        int codeNum=sendMethod(pres);
        switch(codeNum) {
            case 400:
                return new SendDrugsResult(400, "当前订单已发送完毕", null);
            case 401:
                return new SendDrugsResult(401, "当前药品库存不足", null);
            case 402:
            return new SendDrugsResult(402, "配药房数量不足！请先使用库存管理调取药品！", null);
            case 200: {
                List<Prescription> prescriptions = presService.getAll();
                return new SendDrugsResult(200, "发药成功", prescriptions);
            }
        }
        return new SendDrugsResult(403,"未知错误",null);
    }

    @CrossOrigin
    @PostMapping("/api/service/sendDrugs/sendAll")
    @ResponseBody
    public SendDrugsResult sendAll(@RequestBody List<Prescription> pres){
        //System.out.println(pres.get(0).getDrugName());
        List<Prescription> prescripitons=pres;
        int [] codeNum=new int[prescripitons.size()];
        int errorCount=0;
        for(int i=0;i<prescripitons.size();i++)
            codeNum[i]=sendMethod(prescripitons.get(i));
        for(int i=0,j=0;i<codeNum.length;i++)
            if(codeNum[i]!=200){ errorCount++; }//获取出错个数
        if(errorCount==0){
            List<Prescription> prescriptions = presService.getAll();
            return new SendDrugsResult(200, "发药成功", prescriptions);
        }
        else {
            List<Prescription> prescriptions = presService.getAll();
            return new SendDrugsResult(200, "有"+errorCount+"条记录出现问题，请确认", prescriptions);
        }
    }

    public int sendMethod(Prescription pres){
        int num=pres.getNum();
        String drugName=pres.getDrugName();
        int pres_id=pres.getId();

        //通过处方编号查询处方并修改其状态
        Prescription Prescription=presService.getByPresId(pres_id);
        Drug drug= drugService.getByDrugsName(drugName);
        List<Warehouse> warehouses=drug.getWarehouses();

        int sendNum=num/Prescription.getTotalStage(); //当前发送数量等于发送总数量除以疗程数
        if(Prescription.getStatue().equals("已发放")){
            return 400;
        }
        else if(Prescription.getStatue().equals("缺货")){
            return 401;
        }
        else if (drug.getTotalNum()<num) {//总表中数量不足
            Prescription.setStatue("缺货");
           return 401;
        }
        else if(warehouses.get(0).getNum()<num) {//配药房中数量不足
            Prescription.setStatue("药房缺药");
            return 402;
        }
        else if(Prescription.getTotalStage()-Prescription.getCurrentStage()==1) {
            Prescription.setStatue("已发放"); //疗程结束，修改处方状态
            Prescription.setCurrentStage(Prescription.getTotalStage());//增加当前疗程
            Prescription.setSentNum(Prescription.getSentNum()+sendNum);//修改已发药的数量
            warehouses.get(0).setNum(warehouses.get(0).getNum() - sendNum); //修改配药房中数量
            drug.setTotalNum(drug.getTotalNum() - sendNum);//修改总表中数量
        }
        else{
            Prescription.setStatue("暂存"); //疗程开始，修改处方状态
            Prescription.setCurrentStage(Prescription.getCurrentStage()+1);//增加当前疗程
            Prescription.setSentNum(Prescription.getSentNum()+sendNum);//修改已发药的数量
            warehouses.get(0).setNum(warehouses.get(0).getNum() - sendNum); //修改配药房中数量
            drug.setTotalNum(drug.getTotalNum() - sendNum);//修改总表中数量
        }
        drugService.Update(drug);
        presService.Update(Prescription);
        return 200;
    }
}
