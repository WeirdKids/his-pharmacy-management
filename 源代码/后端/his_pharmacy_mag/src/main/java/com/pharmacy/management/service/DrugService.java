package com.pharmacy.management.service;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Warehouse;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:10
 */

public interface DrugService {
    public List<Drug> getByMnemonicCode(String mnemonicCode);
    public List<Drug> getAll();
    public void deleteRepertory(int id);
    public void updateRepertory(int id, double drugsPrice, int totalNum);
    public Drug getById(int id);
}
