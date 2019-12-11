package com.pharmacy.management.controller;

import com.pharmacy.management.result.TreeResult;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class TreeController {

    @Autowired
    DrugService drugService;

    @CrossOrigin
    @PostMapping("/api/getTree")
    @ResponseBody
    public List<TreeResult> getTree(@RequestBody Map item) {
        List<TreeResult> trees = new ArrayList<>();
        int id = (int) item.get("id");
        if (id == 0) {
            List<String> labels = drugService.getDrugsTypeId();
            Collections.sort(labels);
            for (String label : labels) {
                TreeResult tree = new TreeResult(label, null);
                trees.add(tree);
            }
        } else {
            List<String> labels = drugService.getDrugsName(id);
            for (String label : labels) {
                TreeResult tree = new TreeResult(label);
                trees.add(tree);
            }
        }
        return trees;
    }
}
