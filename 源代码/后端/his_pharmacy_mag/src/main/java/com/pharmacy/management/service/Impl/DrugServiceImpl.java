package com.pharmacy.management.service.Impl;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.dao.DrugDao;
import com.pharmacy.management.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:15
 */

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    DrugDao drugDao;

    @Override

    public List<Drug> getByMnemonicCodeLike(String mnemonicCode) {
        return drugDao.findAllByMnemonicCodeLike(mnemonicCode);
    }

    @Override
    public List<Drug> getByDrugsNameLike(String DrugsName){return drugDao.findAllByDrugsNameLike(DrugsName);}

    @Override
    public List<Drug> getAll() {
        return drugDao.returnAll();
    }

    @Override
    public List<Drug> getById(int id){return drugDao.findAllById(id);}
}
