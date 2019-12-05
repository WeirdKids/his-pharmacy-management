package com.pharmacy.management.service.Impl;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Warehouse;
import com.pharmacy.management.dao.DrugDao;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:15
 */

@Service
@Transactional
public class DrugServiceImpl implements DrugService {
    @Autowired
    DrugDao drugDao;

    @Override
    public List<Drug> getByMnemonicCode(String mnemonicCode) {
        return drugDao.findAllByMnemonicCodeLike(mnemonicCode);
    }

    @Override
    public Drug getByDrugsName(String DrugsName){return drugDao.findAllByDrugsName(DrugsName);}

    @Override
    public List<Drug> getAll() {
        return drugDao.returnAll();
    }

    @Override
    public void deleteRepertory(int id) {
        drugDao.deleteById(id);
    }

    @Override
    public void updateRepertory(int id, double drugsPrice, int totalNum) {
        drugDao.updateRepertory(id, drugsPrice, totalNum);
    }

    @Override
    public Drug getById(int id) {
        return drugDao.findById(id);
    }

    @Override
    public void Update(Drug drug){drugDao.save(drug);}

}
