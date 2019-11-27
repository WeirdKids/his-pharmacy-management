package com.pharmacy.management.service.Impl;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.dao.DrugDao;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    DrugDao drugDao;

    @Override
    public List<Drug> getByMnemonicCode(String MnemonicCode) {
        return drugDao.findAllByMnemonicCodeLike(MnemonicCode);
    }

    @Override
    public List<Drug> getByDrugsName(String DrugsName){return drugDao.findALLByDrugsNameLike(DrugsName);}

    @Override
    public List<Drug> getAll() {
        return drugDao.returnAll();
    }

    @Override
    public int getNum() {
        return drugDao.returnNum();
    }
}
