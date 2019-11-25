package com.pharmacy.management.controller;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Repertory;
import com.pharmacy.management.bean.Warehouse;
import com.pharmacy.management.result.RepertoryResult;
import com.pharmacy.management.service.DrugService;
import com.pharmacy.management.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:43
 */

@Controller
public class ReperstoryController {

    @Autowired
    DrugService drugService;
    @Autowired
    WarehouseService warehouseService;

    // 解决跨域问题
    @CrossOrigin
    @PostMapping(value = "/api/repertory/manage/queryAll")
    @ResponseBody
    public RepertoryResult queryAll() {
        List<Drug> drugs = new ArrayList<>();
        List<Warehouse> warehouses = new ArrayList<>();
        drugs = drugService.getAll();
        warehouses = warehouseService.getAll();
        List<Repertory> repertories = new ArrayList<>();
        for (int i = 0; i < warehouses.size(); i ++) {
            Repertory repertory = new Repertory();
            repertory.setId(warehouses.get(i).getId());
            repertory.setWarehouse(warehouses.get(i).getWarehouse());
            repertory.setNum(warehouses.get(i).getNum());
            repertory.setTotalNum(drugs.get(i).getTotalNum());
            repertory.setSaveRequire(drugs.get(i).getSaveRequire());
            repertory.setMnemonicCode(drugs.get(i).getMnemonicCode());
            repertory.setDrugsUnit(drugs.get(i).getDrugsUnit());
            repertory.setDrugsTypeID(drugs.get(i).getDrugsTypeID());
            repertory.setDrugsPrice(drugs.get(i).getDrugsPrice());
            repertory.setDrugsFormat(drugs.get(i).getDrugsFormat());
            repertory.setDrugsDosageID(drugs.get(i).getDrugsDosageID());
            repertory.setDrugsName(drugs.get(i).getDrugsName());
            repertory.setDrugsCode(drugs.get(i).getDrugsCode());
            repertories.add(i, repertory);
        }

        return new RepertoryResult(200, "查询成功！", repertories);
    }
}
