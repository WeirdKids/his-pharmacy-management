package com.pharmacy.management.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pharmacy.management.result.ChartResult;
import com.pharmacy.management.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @CrossOrigin
    @PostMapping("/api/getChart")
    @ResponseBody
    public List<ChartResult> getChart(@RequestBody Map item) {
        String warehouse = (String) item.get("warehouse");
        int Num = 0;
        if (warehouse.equals("储藏室")) {
            Num = 500000;
        } else {
            Num = 5000;
        }
        int num = warehouseService.getNum(warehouse);
        int num1 = Num - num;
        List<ChartResult> results = new ArrayList<>();
        ChartResult chartResult1 = new ChartResult(num, "已占用容量");
        results.add(chartResult1);
        ChartResult chartResult = new ChartResult(num1, "剩余容量");
        results.add(chartResult);
        return results;
    }
}
