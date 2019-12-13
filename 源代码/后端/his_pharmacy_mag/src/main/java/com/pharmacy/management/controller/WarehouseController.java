package com.pharmacy.management.controller;

import com.pharmacy.management.result.ChartResult;
import com.pharmacy.management.result.ChartResults;
import com.pharmacy.management.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @CrossOrigin
    @PostMapping("/api/getChart")
    @ResponseBody
    public ChartResults getChart() {
        int num1 = warehouseService.getNum("储藏室");
        int num2 = warehouseService.getNum("配药房");
        int Num1 = 500000 - num1;
        int Num2 = 5000 - num2;
        List<ChartResult> result1 = new ArrayList<>();
        List<ChartResult> result2 = new ArrayList<>();
        ChartResult chartResult1 = new ChartResult(num1, "已占用容量");
        ChartResult chartResult2 = new ChartResult(Num1, "剩余容量");
        result1.add(chartResult1);
        result1.add(chartResult2);
        ChartResult chartResult3 = new ChartResult(num2, "已占用容量");
        ChartResult chartResult4 = new ChartResult(Num2, "剩余容量");
        result2.add(chartResult3);
        result2.add(chartResult4);
        ChartResults results = new ChartResults(result1, result2);
        return results;
    }
}
