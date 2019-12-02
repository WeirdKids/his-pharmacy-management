package com.pharmacy.management.controller;
import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.PresInfo;
import com.pharmacy.management.bean.Prescription;
import com.pharmacy.management.bean.Warehouse;
import com.pharmacy.management.result.ServiceResult;
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
public class ServiceController {
    @Autowired
    DrugService drugService;
    @Autowired
    PresService presService;

    @CrossOrigin
    @PostMapping("/api/service/sendDrugs/send")//单个发药
    @ResponseBody
    public ServiceResult send(@RequestBody Prescription pres){
        int codeNum=sendMethod(pres);
        switch(codeNum) {
            case 400:
                return new ServiceResult(400, "当前订单已发送完毕", null);
            case 401:
                return new ServiceResult(401, "当前药品库存不足", null);
            case 402:
            return new ServiceResult(402, "配药房数量不足！请先使用库存管理调取药品！", null);
            case 200: {
                List<Prescription> prescriptions = presService.getAll();
                return new ServiceResult(200, "发药成功", prescriptions);
            }
        }
        return new ServiceResult(403,"未知错误",null);
    }

    @CrossOrigin
    @PostMapping("/api/service/sendDrugs/sendAll")//批量发药
    @ResponseBody
    public ServiceResult sendAll(@RequestBody PresInfo presInfo){

        List<Prescription> prescriptions = new ArrayList<>();
        for(int i=0;i<presInfo.getPresId().length;i++){
            prescriptions.add(presService.getByPresId(presInfo.getPresId()[i]));
        }
        int [] codeNum=new int[prescriptions.size()];
        int errorCount=0;
        for(int i=0;i<prescriptions.size();i++)
            codeNum[i]=sendMethod(prescriptions.get(i));
        for(int i=0;i<codeNum.length;i++)
            if(codeNum[i]!=200){ errorCount++; }//获取出错个数
        if(errorCount==0){
            List<Prescription> prescription = presService.getAll();
            return new ServiceResult(200, "操作执行成功", prescription);
        }
        else {
            List<Prescription> prescription = presService.getAll();
            return new ServiceResult(200, "有"+errorCount+"条记录出现问题，请确认", prescription);
        }
    }

    @CrossOrigin
    @PostMapping("/api/service/returnDrugs/returnAllDrugs") //批量退药
    @ResponseBody
    public ServiceResult returnAll(@RequestBody PresInfo presInfo){
        int errorCount=0;
        List<Prescription> prescriptions= new ArrayList<>();
        for(int i=0;i<presInfo.getPresId().length;i++){
            prescriptions.add(presService.getByPresId(presInfo.getPresId()[i]));
        }
        int [] codeNum=new int[prescriptions.size()];
        for(int i=0;i<prescriptions.size();i++)
            codeNum[i]=returnDrugMethod(prescriptions.get(i));
        for(int i=0,j=0;i<codeNum.length;i++)
            if(codeNum[i]!=200){ errorCount++; }//获取出错个数
        if(errorCount==0){
            List<Prescription> prescription = presService.getSent();
            return new ServiceResult(200, "操作执行成功", prescription);
        }
        else {
            List<Prescription> prescription = presService.getSent();
            return new ServiceResult(200, "有"+errorCount+"条记录出现问题，请确认", prescription);
        }
    }

    @CrossOrigin
    @PostMapping("/api/service/returnDrugs/returnDrugs") //单个退药
    @ResponseBody
    public ServiceResult returnDrugs(@RequestBody Prescription pres){
        int codeNum=returnDrugMethod(pres);
        if(codeNum==400)
            return new ServiceResult(400,"当前药品还未发放",null);
        else {
            List<Prescription> prescriptions = presService.getSent();
            return new ServiceResult(200, "操作执行成功", prescriptions);
        }
    }

    @CrossOrigin
    @PostMapping("api/service/managePres/returnPres")
    @ResponseBody
    public ServiceResult returnPres(@RequestBody Prescription pres){
        returnPresMethod(pres);
        List<Prescription> prescriptions=presService.getNotSent();
        return new ServiceResult(200,"已删除处方订单",prescriptions);
    }

    @CrossOrigin
    @PostMapping("api/service/managePres/returnAllPres")
    @ResponseBody
    public ServiceResult returnAllPres(@RequestBody PresInfo presInfo){

        List<Prescription> prescriptions = new ArrayList<>();
        for(int i=0;i<presInfo.getPresId().length;i++){
            prescriptions.add(presService.getByPresId(presInfo.getPresId()[i]));
        }
        int count=prescriptions.size();
        for(int i=0;i<count;i++)
            returnPresMethod(prescriptions.get(i));
        List<Prescription> prescriptions1=presService.getNotSent();
        return new ServiceResult(200,"已删除"+count+"条处方订单",prescriptions1);
    }

    public void returnPresMethod(Prescription pres){
        System.out.println("111");
        int pres_id=pres.getId();
        int drugId=pres.getDrugId();
        int sentNum= pres.getSentNum();

        Drug drug=drugService.getById(drugId);
        List<Warehouse> warehouses=drug.getWarehouses();

        warehouses.get(0).setNum(warehouses.get(0).getNum()+sentNum);//修改配药房中数量
        drug.setTotalNum(drug.getTotalNum()+sentNum);//修改总表中数量
        presService.DeleteById(pres_id);
        drugService.Update(drug);
    }
    public int returnDrugMethod(Prescription pres){
        int drugId=pres.getDrugId();
        int pres_id=pres.getId();

        Prescription prescription=presService.getByPresId(pres_id);
        Drug drug=drugService.getById(drugId);
        List<Warehouse> warehouses=drug.getWarehouses();
        int returnNum=prescription.getSentNum()/prescription.getCurrentStage();
        System.out.println(returnNum);
        if(prescription.getStatue().equals("未发放")){
            return 400;
        }
        else if(prescription.getCurrentStage()-1==0){
            prescription.setStatue("未发放"); //疗程结束，修改处方状态
            prescription.setCurrentStage(prescription.getCurrentStage()-1);//修改当前疗程
            prescription.setSentNum(prescription.getSentNum()-returnNum);//修改已发药数
            warehouses.get(0).setNum(warehouses.get(0).getNum() + returnNum); //修改配药房中数量
            drug.setTotalNum(drug.getTotalNum() + returnNum);//修改总表中数量
            drugService.Update(drug);
            presService.Update(prescription);
            return 200;
        }
        else{
            prescription.setStatue("暂存"); //疗程结束，修改处方状态
            prescription.setCurrentStage(prescription.getCurrentStage()-1);//修改当前疗程
            prescription.setSentNum(prescription.getSentNum()-returnNum);//修改已发药数
            warehouses.get(0).setNum(warehouses.get(0).getNum() + returnNum); //修改配药房中数量
            drug.setTotalNum(drug.getTotalNum() + returnNum);//修改总表中数量
            drugService.Update(drug);
            presService.Update(prescription);
            return 200;
        }
    }
    public int sendMethod(Prescription pres){
        int num=pres.getNum();
        int drugId=pres.getDrugId();
        int pres_id=pres.getId();

        //通过处方编号查询处方并修改其状态
        Prescription Prescription=presService.getByPresId(pres_id);
        Drug drug= drugService.getById(drugId);
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
