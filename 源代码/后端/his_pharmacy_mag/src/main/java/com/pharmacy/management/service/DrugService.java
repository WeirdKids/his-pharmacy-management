package com.pharmacy.management.service;

import com.pharmacy.management.bean.Drug;

import java.util.List;

/**
 * @author 徐奥飞
 * @date 2019-11-8 8:10
 */

public interface DrugService {
    public List<Drug> getByMnemonicCode(String MnemonicCode);
    public List<Drug> getAll();
    public int getNum();
}
