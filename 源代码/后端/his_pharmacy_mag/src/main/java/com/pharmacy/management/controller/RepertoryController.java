package com.pharmacy.management.controller;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Warehouse;
import com.pharmacy.management.dao.DrugDao;
import com.pharmacy.management.result.RepertoryResult;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class RepertoryController {

    @Autowired
    DrugService drugService;

    @Autowired
    DrugDao drugDao;

    // 解决跨域问题
    @CrossOrigin
    @PostMapping(value = "/api/query")
    @ResponseBody
    public RepertoryResult query(@RequestBody Drug requestDrug) {
        String mnemonicCode = requestDrug.getMnemonicCode();
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getByMnemonicCode("%"+ mnemonicCode +"%");
        if (drugs.size() == 0) {
            return new RepertoryResult(400, "没有匹配数据", null);
        } else {
            return new RepertoryResult(200, "操作执行成功", drugs);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/queryAll")
    @ResponseBody
    public RepertoryResult queryAll() {
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getAll();
        return new RepertoryResult(200, "操作执行成功", drugs);
    }

    @CrossOrigin
    @PostMapping(value = "/api/deleteRepertory")
    @ResponseBody
    public RepertoryResult deleteRepertory(@RequestBody Drug requestDrug) {
        int id = requestDrug.getId();
        String mnemonicCode = requestDrug.getMnemonicCode();
        drugService.deleteRepertory(id);
        List<Drug>  drugs = new ArrayList<>();
        drugs = getDrugs(mnemonicCode);
        return new RepertoryResult(200, "删除完成", drugs);
    }

    @CrossOrigin
    @PostMapping(value = "/api/updateRepertory")
    @ResponseBody
    @Transactional
    public RepertoryResult updateRepertory(@RequestBody Drug requestDrug) {
        int id = requestDrug.getId();
        double drugsPrice = requestDrug.getDrugsPrice();
        int totalNum = requestDrug.getTotalNum();
        String mnemonicCode = requestDrug.getMnemonicCode();
        Drug drug = drugService.getById(id);
        drug.setDrugsPrice(drugsPrice);
        drug.setTotalNum(totalNum);
        List<Warehouse> warehouses = drug.getWarehouses();
        List<Warehouse> warehouses1 = requestDrug.getWarehouses();
        while (warehouses.size() != 2) {
            Warehouse warehouse = new Warehouse();
            warehouse.setDrug(drug);
            warehouse.setNum(0);
            warehouse.setWarehouse("0");
            warehouses.add(warehouse);
        }
        if (warehouses.get(0).getWarehouse() == warehouses1.get(0).getWarehouse()) {
            warehouses.get(0).setNum(warehouses1.get(0).getNum());
            warehouses.get(1).setWarehouse(warehouses1.get(1).getWarehouse());
            warehouses.get(1).setNum(warehouses1.get(1).getNum());
        } else {
            warehouses.get(0).setWarehouse(warehouses1.get(1).getWarehouse());
            warehouses.get(0).setNum(warehouses1.get(1).getNum());
            warehouses.get(1).setWarehouse(warehouses1.get(0).getWarehouse());
            warehouses.get(1).setNum(warehouses1.get(0).getNum());
        }
        for (int i = warehouses.size(); i > 0; i --) {
            if (warehouses.get(i - 1).getNum() == 0) {
                warehouses.remove(i - 1);
            }
        }
        drugDao.save(drug);
        List<Drug> drugs = getDrugs(mnemonicCode);
        return new RepertoryResult(200, "修改完成", drugs);
    }

    @CrossOrigin
    @PostMapping(value = "/api/updateWarehouse")
    @ResponseBody
    @Transactional
    public RepertoryResult updateWarehouse(@RequestBody Map drugsOptions) {
        List<Integer> ids = new ArrayList<>();
        ids = (List<Integer>) drugsOptions.get("ids");
        String mnemonicCode = (String) drugsOptions.get("mnemonicCode");
        String warehouse = (String) drugsOptions.get("warehouse");
        System.out.println(ids);
        System.out.println(mnemonicCode);
        System.out.println(warehouse);
        for (int id : ids) {
            Drug drug = new Drug();
            drug = drugService.getById(id);
            int size = drug.getWarehouses().size();
            if (size == 0) {
                continue;
            } else if (size == 1) {
                drug.getWarehouses().get(0).setWarehouse(warehouse);
            } else {
                drug.getWarehouses().get(0).setNum(drug.getWarehouses().get(0).getNum() + drug.getWarehouses().get(1).getNum());
                drug.getWarehouses().remove(1);
                drug.getWarehouses().get(0).setWarehouse(warehouse);
            }
            drugDao.save(drug);
        }
        List<Drug> drugs = new ArrayList<>();
        drugs = getDrugs(mnemonicCode);
        return new RepertoryResult(200, "批量修改完成", drugs);
    }

    @CrossOrigin
    @PostMapping(value = "/api/addRepertory")
    @ResponseBody
    @Transactional
    public RepertoryResult addRepertory(@RequestBody Drug requestDrug) {
        drugDao.save(requestDrug);
        List<Drug> drugs = new ArrayList<>();
        drugs = drugService.getAll();
        return new RepertoryResult(200, "添加药品成功", drugs);
    }

    @CrossOrigin
    @PostMapping(value = "/api/deleteOptions")
    @ResponseBody
    public RepertoryResult deleteOptions(@RequestBody Map drugsOptions) {
        List<Integer> ids = new ArrayList<>();
        ids = (List<Integer>) drugsOptions.get("ids");
        String mnemonicCode = (String) drugsOptions.get("mnemonicCode");
        for (int id : ids) {
            drugService.deleteRepertory(id);
        }
        List<Drug> drugs = new ArrayList<>();
        drugs = getDrugs(mnemonicCode);
        return new RepertoryResult(200, "批量删除完成", drugs);
    }

    private List<Drug> getDrugs(String mnemonicCode) {
        List<Drug> drugs = new ArrayList<>();
        if (mnemonicCode.isEmpty()) {
            drugs = drugService.getAll();
        } else {
            drugs = drugService.getByMnemonicCode("%"+ mnemonicCode +"%");
        }
        return drugs;
    }
}
