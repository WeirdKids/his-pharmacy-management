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
import java.util.Date;
import java.util.List;

@Controller
public class PresManageController {
    @Autowired
    DrugService drugService;
    @Autowired
    PresService presService;


    @CrossOrigin
    @PostMapping("api/service/managePres/deletePres")
    @ResponseBody
    public ServiceResult deletePres(@RequestBody Prescription pres){
        deletePresMethod(pres);
        List<Prescription> prescriptions=presService.getNotSent();
        return new ServiceResult(200,"已删除处方订单",prescriptions);
    }

    @CrossOrigin
    @PostMapping("api/service/managePres/deleteAllPres")
    @ResponseBody
    public ServiceResult deleteAllPres(@RequestBody PresInfo presInfo){

        List<Prescription> prescriptions = new ArrayList<>();
        for(int i=0;i<presInfo.getPresIds().length;i++){
            prescriptions.add(presService.getByPresId(presInfo.getPresIds()[i]));
        }
        int count=prescriptions.size();
        for(int i=0;i<count;i++)
            deletePresMethod(prescriptions.get(i));
        List<Prescription> prescriptions1=presService.getNotSent();
        return new ServiceResult(200,"已删除"+count+"条处方订单",prescriptions1);
    }

    @CrossOrigin
    @PostMapping("api/service/managePres/addPres")
    @ResponseBody
    public ServiceResult addPres(@RequestBody Prescription pres){
        Drug drug=drugService.getById(pres.getDrugId());
        pres.setStatue("未发放");
        pres.setCurrentStage(0);
        pres.setSentNum(0);
        pres.setDrugName(drug.getDrugsName());

        presService.Update(pres);
        List<Prescription> prescriptions = presService.getAll();
        return new ServiceResult(200, "添加订单成功", prescriptions);
    }
    @CrossOrigin
    @PostMapping("api/service/managePres/addPresByFile")
    @ResponseBody
    public ServiceResult addPresByFile(@RequestBody PresInfo presInfo){
        List<Prescription> prescriptions = new ArrayList<>();
        for(int i=0;i<presInfo.getPrescriptionCodes().length;i++){
            prescriptions.get(i).setPrescriptionCode(presInfo.getPrescriptionCodes()[i]);
            prescriptions.get(i).setDoctorID(presInfo.getDoctorIDs()[i]);
            prescriptions.get(i).setCharger(presInfo.getChargers().get(i));
            prescriptions.get(i).setChargeTime(presInfo.getChargeTimes().get(i));
            prescriptions.get(i).setStatue(presInfo.getStatues().get(i));
            prescriptions.get(i).setTotalStage(presInfo.getTotalStages()[i]);
            prescriptions.get(i).setCurrentStage(presInfo.getCurrentStages()[i]);
            prescriptions.get(i).setNum(presInfo.getNums()[i]);
            prescriptions.get(i).setSentNum(presInfo.getSentNums()[i]);
            prescriptions.get(i).setDrugName(presInfo.getDrugNames().get(i));
            prescriptions.get(i).setDrugId(presInfo.getDrugIds()[i]);
            presService.Update(prescriptions.get(i));
        }
        List<Prescription> prescriptions1 = presService.getAll();
        return new ServiceResult(200, "添加订单成功", prescriptions1);
    }

    public void deletePresMethod(Prescription pres){
        System.out.println("111");
        int pres_id=pres.getId();
        int drugId=pres.getDrugId();
        int sentNum= pres.getSentNum();

        Drug drug=drugService.getById(drugId);
        List<Warehouse> warehouses=drug.getWarehouses();

        presService.DeleteById(pres_id);
        drugService.Update(drug);
    }
}
