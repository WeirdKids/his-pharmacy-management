package com.pharmacy.management.controller;
import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.PresInfo;

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
        for(int i=0;i<presInfo.getPresId().length;i++){
            prescriptions.add(presService.getByPresId(presInfo.getPresId()[i]));
        }
        int count=prescriptions.size();
        for(int i=0;i<count;i++)
            deletePresMethod(prescriptions.get(i));
        List<Prescription> prescriptions1=presService.getNotSent();
        return new ServiceResult(200,"已删除"+count+"条处方订单",prescriptions1);
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
